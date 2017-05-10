package com.thinkequip.bizfw.auth.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.auth.dao.MenuDao;
import com.thinkequip.bizfw.auth.model.Menu;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.base.impl.BaseDaoImpl;

/**
 * 菜单dao实现
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

	@Override
	public Menu getRootMenu() throws BizfwServiceException {
		List<Menu> list = queryByFieldAndValue(Menu.COLUMN_NAME, Menu.ROOT_NAME);
		if (ListUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
