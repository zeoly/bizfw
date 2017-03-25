package com.thinkequip.bizfw.auth.service;

import java.util.List;

import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.po.model.People;

/**
 * 角色服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
public interface RoleService extends BaseService<Role> {

	/**
	 * 获取所有角色列表
	 * 
	 * @return 角色列表
	 * @throws BizfwServiceException
	 */
	public List<Role> getAllRoleList() throws BizfwServiceException;

	/**
	 * 添加角色
	 * 
	 * @param role
	 *            角色
	 * @throws BizfwServiceException
	 */
	public void addRole(Role role) throws BizfwServiceException;

	/**
	 * 修改角色
	 * 
	 * @param role
	 *            角色
	 * @throws BizfwServiceException
	 */
	public void modify(Role role) throws BizfwServiceException;

	/**
	 * 根据id删除角色
	 * 
	 * @param roleId
	 *            角色id
	 * @throws BizfwServiceException
	 */
	public void deleteRole(String roleId) throws BizfwServiceException;

	/**
	 * 获取人员关联的角色列表
	 * 
	 * @param peopleId
	 *            人员id
	 * @return 角色列表
	 * @throws BizfwServiceException
	 */
	public List<Role> getRoleListByPeople(String peopleId) throws BizfwServiceException;

	/**
	 * 设置人员关联的角色列表
	 * 
	 * @param people
	 *            人员
	 * @param roleIdList
	 *            角色id列表
	 * @throws BizfwServiceException
	 */
	public void setRoleOfPeople(People people, List<String> roleIdList) throws BizfwServiceException;

	/**
	 * 获取角色关联的人员数量
	 * 
	 * @param role
	 *            角色
	 * @return 关联数量
	 * @throws BizfwServiceException
	 */
	public long countPeopleByRole(Role role) throws BizfwServiceException;

}
