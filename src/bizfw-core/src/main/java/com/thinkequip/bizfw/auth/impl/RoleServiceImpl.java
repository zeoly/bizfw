package com.thinkequip.bizfw.auth.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkequip.bizfw.auth.dao.PeopleRoleRelDao;
import com.thinkequip.bizfw.auth.dao.RoleDao;
import com.thinkequip.bizfw.auth.model.PeopleRoleRelation;
import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.auth.service.RoleService;
import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.base.consts.ErrorCode;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.po.model.People;

/**
 * 角色服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PeopleRoleRelDao peopleRoleRelDao;

	@Override
	public List<Role> getAllRoleList() throws BizfwServiceException {
		return roleDao.list();
	}

	@Transactional
	@Override
	public void addRole(Role role) throws BizfwServiceException {
		List<Role> tmpRole = queryByFieldAndValue(Role.COLUMN_NAME, role.getName());
		if (ListUtils.isNotEmpty(tmpRole)) {
			throw new BizfwServiceException(ErrorCode.Auth.Role.ADD_FAIL_EXISTED);
		}
		save(role);
	}

	@Transactional
	@Override
	public void modify(Role role) throws BizfwServiceException {
		List<Role> tmpRole = queryByFieldAndValue(Role.COLUMN_NAME, role.getName());
		if (ListUtils.isNotEmpty(tmpRole) && !role.getIdBfRole().equals(tmpRole.get(0).getIdBfRole())) {
			throw new BizfwServiceException(ErrorCode.Auth.Role.MOD_FAIL_EXISTED);
		}
		Role dbRole = queryById(role.getIdBfRole());
		dbRole.setName(role.getName());
		dbRole.setDescription(role.getDescription());
		dbRole.update(role.getUpdateBy());
		update(dbRole);
	}

	@Transactional
	@Override
	public void deleteRole(String roleId) throws BizfwServiceException {
		Role role = queryById(roleId);
		checkObjectNotNull(role, "角色[" + roleId + "]", "删除角色");
		checkCanDeleteRole(role);
		delete(role.getIdBfRole());
	}

	@Transactional
	@Override
	public void setRoleOfPeople(People people, List<String> roleIdList) throws BizfwServiceException {
		deletePeopleRoleRelation(people);
		for (String roleId : roleIdList) {
			Role role = queryById(roleId);
			if (role == null) {
				throw new BizfwServiceException(ErrorCode.PeopleDept.People.SET_ROLE_REL_FAIL_NOT_FOUND, roleId);
			}
			PeopleRoleRelation peopleRoleRelation = new PeopleRoleRelation(people.getUpdateBy(), people.getIdBfPeople(),
					roleId);
			peopleRoleRelDao.save(peopleRoleRelation);
		}
	}

	@Override
	public List<Role> getRoleListByPeople(String peopleId) throws BizfwServiceException {
		List<Role> roleList = new ArrayList<Role>();
		List<PeopleRoleRelation> relationList = peopleRoleRelDao
				.queryByFieldAndValue(PeopleRoleRelation.COLUMN_PEOPLE_ID, peopleId);
		for (PeopleRoleRelation relation : relationList) {
			Role role = queryById(relation.getRoleId());
			roleList.add(role);
		}
		return roleList;
	}

	@Override
	public long countPeopleByRole(Role role) throws BizfwServiceException {
		long count = peopleRoleRelDao.getCountByFieldAndValue(PeopleRoleRelation.COLUMN_ROLE_ID, role.getIdBfRole());
		return count;
	}

	/**
	 * 根据人员删除人员角色关联关系
	 * 
	 * @param people
	 *            人员
	 * @throws BizfwServiceException
	 */
	private void deletePeopleRoleRelation(People people) throws BizfwServiceException {
		peopleRoleRelDao.deleteByFieldAndValue(PeopleRoleRelation.COLUMN_PEOPLE_ID, people.getIdBfPeople());
	}

	/**
	 * 检查角色是否可删除
	 * 
	 * @param role
	 *            角色
	 * @throws BizfwServiceException
	 */
	private void checkCanDeleteRole(Role role) throws BizfwServiceException {
		long peopleCount = countPeopleByRole(role);
		if (peopleCount > 0) {
			throw new BizfwServiceException(ErrorCode.Auth.Role.DEL_FAIL_WITH_PEOPLE);
		}
	}

	@Override
	public BaseDao<Role> getBaseDao() {
		return roleDao;
	}

}
