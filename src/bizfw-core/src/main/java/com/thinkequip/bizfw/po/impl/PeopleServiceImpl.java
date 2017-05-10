package com.thinkequip.bizfw.po.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkequip.bizfw.auth.dao.PeopleRoleRelDao;
import com.thinkequip.bizfw.auth.model.PeopleRoleRelation;
import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.PropertiesUtils;
import com.thinkequip.bizfw.base.common.StringUtils;
import com.thinkequip.bizfw.base.consts.ErrorCode;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.po.dao.PeopleDao;
import com.thinkequip.bizfw.po.model.Department;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.bizfw.po.service.DepartmentService;
import com.thinkequip.bizfw.po.service.PeopleService;

/**
 * 人员服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Service("peopleService")
public class PeopleServiceImpl extends BaseServiceImpl<People> implements PeopleService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private PeopleDao peopleDao;

	@Autowired
	private PeopleRoleRelDao peopleRoleRelDao;

	@Transactional
	@Override
	public String addPeople(People people) throws BizfwServiceException {
		logger.info("{}新增人员{}", people.getUpdateBy(), people.getCode());
		People tmpPeople = getByCode(people.getCode());
		if (tmpPeople != null) {
			logger.error("{}新增人员{}失败，人员已存在", people.getUpdateBy(), people.getCode());
			throw new BizfwServiceException(ErrorCode.PeopleDept.People.ADD_FAIL_EXISTED, people.getCode());
		}
		Department department = departmentService.queryById(people.getDepartmentId());
		if (department == null) {
			logger.error("{}新增人员{}失败，机构{}为空", people.getUpdateBy(), people.getCode(), people.getDepartmentId());
			throw new BizfwServiceException(ErrorCode.PeopleDept.People.ADD_FAIL_WITHOUT_DEPT);
		}
		people.setErrorCount(0);
		people.setStatus(People.STATUS_NORMAL);
		people.setPassword(StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd")));
		return save(people);
	}

	@Override
	public void modifyPeople(People people) throws BizfwServiceException {
		logger.info("{}修改人员{}操作开始", people.getUpdateBy(), people.getCode());
		update(people);
		logger.info("{}修改人员{}操作完成", people.getUpdateBy(), people.getCode());
	}

	@Transactional
	@Override
	public void deletePeople(People people) throws BizfwServiceException {
		logger.info("{}删除人员{}操作开始", people.getUpdateBy(), people.getCode());
		if (people.getCode().equals(people.getUpdateBy())) {
			logger.error("{}删除自己失败", people.getUpdateBy());
			throw new BizfwServiceException(ErrorCode.PeopleDept.People.DEL_FAIL_SELF);
		}
		delete(people.getIdBfPeople());
		peopleRoleRelDao.deleteByFieldAndValue(PeopleRoleRelation.COLUMN_PEOPLE_ID, people.getIdBfPeople());
		logger.info("{}删除人员{}操作完成", people.getUpdateBy(), people.getCode());
	}

	@Transactional
	@Override
	public People getByCode(String code) throws BizfwServiceException {
		List<People> peopleInfoList = queryByFieldAndValue(People.COLUMN_CODE, code);
		if (peopleInfoList.isEmpty()) {
			return null;
		}
		if (peopleInfoList.size() > 1) {
			throw new BizfwServiceException(ErrorCode.PeopleDept.People.QUERY_FAIL_DUPLICATED);
		}
		People p = peopleInfoList.get(0);
		return p;
	}

	@Override
	public long getPeopleCountByDepartment(Department department) throws BizfwServiceException {
		long count = peopleDao.getCountByFieldAndValue(People.COLUMN_DEPARTMENT_ID, department.getIdBfDepartment());
		return count;
	}

	@Override
	public List<People> getPeopleListByDepartment(String departmentId) throws BizfwServiceException {
		List<People> list = queryByFieldAndValue(People.COLUMN_DEPARTMENT_ID, departmentId);
		return list;
	}

	@Override
	public void unlock(People people) throws BizfwServiceException {
		logger.info("{}解锁人员{}操作开始", people.getUpdateBy(), people.getCode());
		if (!People.STATUS_LOCKED.equals(people.getStatus())) {
			logger.error("{}解锁人员{}操作失败, 人员状态为{}", people.getUpdateBy(), people.getCode(), people.getStatus());
			throw new BizfwServiceException(ErrorCode.PeopleDept.People.UNLOCK_FAIL_STATUS_ERR);
		}
		people.update();
		people.setStatus(People.STATUS_NORMAL);
		people.setPassword(StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd")));
		people.setErrorCount(0);
		update(people);
		logger.info("{}解锁人员{}操作结束", people.getUpdateBy(), people.getCode());
	}

	@Override
	public void modifyPassword(People people, String oldPwd, String newPwd) throws BizfwServiceException {
		logger.info("{}修改密码操作开始", people.getCode());
		String oldMd5 = StringUtils.encryptMD5(oldPwd);
		if (!oldMd5.equals(people.getPassword())) {
			throw new BizfwServiceException(ErrorCode.PeopleDept.People.UPDATE_FAIL_PWD_ERR);
		}
		people.setPassword(StringUtils.encryptMD5(newPwd));
		update(people);
		logger.info("{}修改密码操作结束", people.getCode());
	}

	@Override
	public BaseDao<People> getBaseDao() {
		return peopleDao;
	}

}
