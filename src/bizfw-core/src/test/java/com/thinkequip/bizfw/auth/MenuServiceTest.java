package com.thinkequip.bizfw.auth;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.thinkequip.bizfw.auth.model.Menu;
import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.auth.service.MenuService;
import com.thinkequip.bizfw.auth.service.RoleService;
import com.thinkequip.bizfw.base.BaseTest;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.bizfw.po.service.PeopleService;

public class MenuServiceTest extends BaseTest {

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PeopleService peopleService;

	// @Test
	@Rollback(false)
	public void testAdd() {
		try {
			Menu menu = new Menu("test33", "测试菜单33", "/urltest/test/tt.jsp", "000003",
					"8a80808658baf3900158baf395030000");
			menuService.addMenu(menu);

			Menu rootMenu = menuService.queryById("8a80808758354eb20158354ee4240000");
			List<Menu> list = menuService.getChildMenuList(rootMenu);
			for (Menu child : list) {
				System.out.println(child.getName());
			}
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void testModify() {
		try {
			Menu menu = menuService.queryById("8a8080855891b1a6015891b1a9a90000");
			menu.setName("测试菜单1");
			menuService.modifyMenu(menu);

			Menu rootMenu = menuService.queryById("8a80808758354eb20158354ee4240000");
			List<Menu> list = menuService.getChildMenuList(rootMenu);
			for (Menu child : list) {
				System.out.println(child.getName());
			}
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void testDelete() {
		try {
			menuService.deleteMenu("8a8080855891b294015891b298060000");
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void testSetMenuOfRole() {
		try {
			Role role = roleService.queryById("8a8080875825fe41015825ff02d60002");
			List<String> list = new ArrayList<String>();
			list.add("8a80808658baf3430158baf347c60000");
			list.add("8a80808658baf3900158baf395030000");
			list.add("8a80808658baf43e0158baf442300000");
			list.add("8a80808658baf49d0158baf4a2300000");
			list.add("8a80808658baf4d40158baf4d7dd0000");
			list.add("8a80808658baf51f0158baf523420000");
			list.add("8a80808658baf5550158baf559130000");
			list.add("8a80808658baf58a0158baf58ec60000");
			menuService.setMenuOfRole(role, list);
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void testGetMenuListByRole() {
		try {
			Role role = roleService.queryById("8a8080875825fe41015825fe6ad40000");
			List<Menu> list = menuService.getMenuListByRole(role);
			for (Menu menu : list) {
				System.out.println(menu.getName());
			}
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	@Test
	@Rollback(false)
	public void testGetMenuListByPeople() {
		try {
			People people = peopleService.getByCode("rrrr");
			Menu r = menuService.getMenuTreeByPeople(people.getIdBfPeople());
			printMenu(r);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void printMenu(Menu menu) {
		System.out.println(menu.getName());
		if (ListUtils.isNotEmpty(menu.getChildList())) {
			for (Menu m : menu.getChildList()) {
				printMenu(m);
			}
		}
	}
}
