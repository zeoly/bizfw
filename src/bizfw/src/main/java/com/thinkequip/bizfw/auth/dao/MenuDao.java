package com.thinkequip.bizfw.auth.dao;

import com.thinkequip.bizfw.auth.model.Menu;
import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;

/**
 * 角色dao接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
public interface MenuDao extends BaseDao<Menu> {

	/**
	 * 获取系统根菜单
	 * 
	 * @return 根菜单
	 * @throws BizfwServiceException
	 */
	public Menu getRootMenu() throws BizfwServiceException;

}
