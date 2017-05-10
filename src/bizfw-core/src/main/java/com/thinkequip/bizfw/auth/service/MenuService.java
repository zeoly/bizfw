package com.thinkequip.bizfw.auth.service;

import java.util.List;

import com.thinkequip.bizfw.auth.model.Menu;
import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;

/**
 * 菜单服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
public interface MenuService extends BaseService<Menu> {

	/**
	 * 获取整个菜单树结构
	 * 
	 * @return 菜单树
	 * @throws BizfwServiceException
	 */
	public Menu getAllMenuTree() throws BizfwServiceException;

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 *            菜单
	 * @throws BizfwServiceException
	 */
	public void addMenu(Menu menu) throws BizfwServiceException;

	/**
	 * 修改菜单
	 * 
	 * @param menu
	 *            菜单
	 * @throws BizfwServiceException
	 */
	public void modifyMenu(Menu menu) throws BizfwServiceException;

	/**
	 * 根据id删除菜单
	 * 
	 * @param menuId
	 *            菜单id
	 * @throws BizfwServiceException
	 */
	public void deleteMenu(String menuId) throws BizfwServiceException;

	/**
	 * 设置角色对应菜单组
	 * 
	 * @param role
	 *            角色
	 * @param menuIdList
	 *            菜单列表
	 * @throws BizfwServiceException
	 */
	public void setMenuOfRole(Role role, List<String> menuIdList) throws BizfwServiceException;

	/**
	 * 获取当前菜单下子菜单列表
	 * 
	 * @param menu
	 *            菜单
	 * @return 菜单列表
	 * @throws BizfwServiceException
	 */
	public List<Menu> getChildMenuList(Menu menu) throws BizfwServiceException;

	/**
	 * 获取角色对应的菜单列表
	 * 
	 * @param role
	 *            角色
	 * @return 菜单列表
	 * @throws BizfwServiceException
	 */
	public List<Menu> getMenuListByRole(Role role) throws BizfwServiceException;

	/**
	 * 获取人员对应的菜单树
	 * 
	 * @param peopleId
	 *            人员id
	 * @return 菜单树
	 * @throws BizfwServiceException
	 */
	public Menu getMenuTreeByPeople(String peopleId) throws BizfwServiceException;

	/**
	 * 将菜单列表转换为菜单树结构
	 * 
	 * @param list
	 *            菜单列表
	 * @param rootMenu
	 *            根菜单
	 * @return 菜单树
	 * @throws BizfwServiceException
	 */
	public Menu convertListToTree(List<Menu> list, Menu rootMenu) throws BizfwServiceException;

	/**
	 * 获取当前菜单下子菜单数量
	 * 
	 * @param menu
	 *            菜单
	 * @return 子菜单数量
	 * @throws BizfwServiceException
	 */
	public long countChildMenu(Menu menu) throws BizfwServiceException;

	/**
	 * 删除与菜单关联的角色关系
	 * 
	 * @param menuId
	 *            菜单id
	 * @throws BizfwServiceException
	 */
	public void deleteRoleMenuRelationByMenu(String menuId) throws BizfwServiceException;

}
