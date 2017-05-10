package com.thinkequip.bizfw.po.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.base.consts.ErrorCode;
import com.thinkequip.bizfw.base.consts.SystemConsts;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.po.dao.DepartmentDao;
import com.thinkequip.bizfw.po.dao.DepartmentRelationDao;
import com.thinkequip.bizfw.po.model.Department;
import com.thinkequip.bizfw.po.model.DepartmentRelation;
import com.thinkequip.bizfw.po.service.DepartmentService;
import com.thinkequip.bizfw.po.service.PeopleService;

/**
 * 机构服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private DepartmentRelationDao departmentRelationDao;

	@Transactional
	@Override
	public void addDepartment(Department department) throws BizfwServiceException {
		logger.info("{}新增机构{}操作开始", department.getUpdateBy(), department.getCode());
		Department parentDept = queryById(department.getParentDepartmentId());
		department.setLevel(parentDept.getLevel() + 1);
		save(department);
		saveDepartmentRelation(department, parentDept);
		logger.info("{}新增机构{}操作结束", department.getUpdateBy(), department.getCode());
	}

	@Override
	public void modifyDepartment(Department department) throws BizfwServiceException {
		logger.info("{}修改机构{}操作开始", department.getUpdateBy(), department.getCode());
		Department dbDepartment = queryById(department.getIdBfDepartment());
		dbDepartment.setName(department.getName());
		dbDepartment.setCode(department.getCode());
		dbDepartment.update(department.getUpdateBy());
		update(dbDepartment);
		logger.info("{}修改机构{}操作结束", department.getUpdateBy(), department.getCode());
	}

	@Override
	public void deleteDepartment(Department department) throws BizfwServiceException {
		logger.info("{}删除机构{}操作开始", department.getUpdateBy(), department.getCode());
		checkObjectNotNull(department, "机构[" + department.getIdBfDepartment() + "]", "删除机构");
		checkCanDelete(department);
		delete(department.getIdBfDepartment());
		deleteUpperDepartmentRelation(department);
		logger.info("{}删除机构{}操作结束", department.getUpdateBy(), department.getCode());
	}

	@Override
	public void deleteUpperDepartmentRelation(Department department) throws BizfwServiceException {
		departmentRelationDao.deleteByFieldAndValue(DepartmentRelation.COLUMN_CHILD_DEPARTMENT_ID,
				department.getIdBfDepartment());
	}

	@Override
	public Department queryByCode(String code) throws BizfwServiceException {
		List<Department> list = queryByFieldAndValue(Department.COLUMN_CODE, code);
		if (ListUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Department getParentDepartment(Department department) throws BizfwServiceException {
		DepartmentRelation parentDepartmentRel = departmentRelationDao.getParentDepartmentRel(department);
		Department parentDepartment = queryById(parentDepartmentRel.getParentDepartmentId());
		return parentDepartment;
	}

	@Override
	public List<Department> getChildDepartmentList(String deparmentId) throws BizfwServiceException {
		List<Department> childList = departmentDao.queryByFieldAndValue(Department.COLUMN_PARENT_DEPT_ID, deparmentId);
		return childList;
	}

	@Override
	public List<Department> getAllParentDeptmentList(String deparmentId) throws BizfwServiceException {
		List<Department> deptList = new ArrayList<Department>();
		List<DepartmentRelation> relationList = departmentRelationDao
				.queryByFieldAndValue(DepartmentRelation.COLUMN_CHILD_DEPARTMENT_ID, deparmentId);
		for (DepartmentRelation relation : relationList) {
			Department parentDepartment = queryById(relation.getParentDepartmentId());
			deptList.add(parentDepartment);
		}
		return deptList;
	}

	@Override
	public List<Department> getAllChildDeptmentList(String deparmentId) throws BizfwServiceException {
		List<Department> deptList = new ArrayList<Department>();
		List<DepartmentRelation> relationList = departmentRelationDao
				.queryByFieldAndValue(DepartmentRelation.COLUMN_PARENT_DEPARTMENT_ID, deparmentId);
		for (DepartmentRelation relation : relationList) {
			Department childDepartment = queryById(relation.getChildDepartmentId());
			deptList.add(childDepartment);
		}
		return deptList;
	}

	@Override
	public boolean hasChildDepartment(Department department) throws BizfwServiceException {
		long count = departmentRelationDao.getCountByFieldAndValue(DepartmentRelation.COLUMN_PARENT_DEPARTMENT_ID,
				department.getIdBfDepartment());
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPeople(Department department) throws BizfwServiceException {
		long count = peopleService.getPeopleCountByDepartment(department);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Department getDepartmentTreeByDepartmentId(String deparmentId) throws BizfwServiceException {
		Department department = queryById(deparmentId);
		List<Department> childDepartmentList = getChildDepartmentList(deparmentId);
		ListUtils.sort(childDepartmentList, Department.COLUMN_CODE);
		for (Department childDepartment : childDepartmentList) {
			childDepartment = getDepartmentTreeByDepartmentId(childDepartment.getIdBfDepartment());
		}
		department.setChildDepartmentList(childDepartmentList);
		return department;
	}

	/**
	 * 保存机构关联关系
	 * 
	 * @param department
	 *            机构
	 * @param parentDepartment
	 *            父机构
	 * @throws BizfwServiceException
	 */
	private void saveDepartmentRelation(Department department, Department parentDepartment)
			throws BizfwServiceException {
		List<Department> parentDeptList = getAllParentDeptmentList(parentDepartment.getIdBfDepartment());
		parentDeptList.add(parentDepartment);
		for (Department dept : parentDeptList) {
			DepartmentRelation relation = new DepartmentRelation(SystemConsts.SYSTEM);
			relation.setChildDepartmentId(department.getIdBfDepartment());
			relation.setParentDepartmentId(dept.getIdBfDepartment());
			relation.setParentLevel(dept.getLevel());
			departmentRelationDao.save(relation);
		}
	}

	/**
	 * 检查机构是否可删除
	 * 
	 * @param department
	 *            机构
	 * @throws BizfwServiceException
	 */
	private void checkCanDelete(Department department) throws BizfwServiceException {
		boolean hasChildDepartment = hasChildDepartment(department);
		if (hasChildDepartment) {
			logger.error("{}删除机构{}操作失败，存在子机构", department.getUpdateBy(), department.getCode());
			throw new BizfwServiceException(ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_CHILD);
		}
		boolean hasPeople = hasPeople(department);
		if (hasPeople) {
			logger.error("{}删除机构{}操作失败，存在人员", department.getUpdateBy(), department.getCode());
			throw new BizfwServiceException(ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_PEOPLE);
		}
	}

	@Override
	public BaseDao<Department> getBaseDao() {
		return departmentDao;
	}

}
