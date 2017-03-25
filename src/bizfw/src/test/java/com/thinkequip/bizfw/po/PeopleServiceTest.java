package com.thinkequip.bizfw.po;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.thinkequip.bizfw.base.BaseTest;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.po.model.Department;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.bizfw.po.service.DepartmentService;
import com.thinkequip.bizfw.po.service.PeopleService;

public class PeopleServiceTest extends BaseTest {

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private DepartmentService departmentService;

	// @Test
	@Rollback(false)
	public void testAddPeople() {
		try {
			Department department = departmentService.queryByCode("0");

			People people = new People("test");
			people.setCode("zengyongli");
			people.setName("曾永理");
			people.setDepartmentId(department.getIdBfDepartment());
			String a = peopleService.addPeople(people);
			System.out.println(a);
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void test() {
		try {
			Department department = departmentService.queryByCode("root");

			People people = new People("test");
			people.setCode("test");
			people.setName("测试人员");
			people.setDepartmentId(department.getIdBfDepartment());

			peopleService.addPeople(people);

			people = peopleService.getByCode("test");
			System.out.println(people.getName());
			people.setName("测试人员1");
			peopleService.modifyPeople(people);

			people = peopleService.getByCode("test");
			System.out.println(people.getName());
			peopleService.deletePeople(people);
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	@Test
	@Rollback(false)
	public void testUnlock() {
		try {
			People people = peopleService.getByCode("admin");
			peopleService.unlock(people);
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void testGetPeopleListByDepartment() {
		try {
			List<People> list = peopleService.getPeopleListByDepartment("8a808087583fa7b701583faadf300000");
			for (People people : list) {
				System.out.println(people.getName() + "," + people.getCode());
			}
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}
}
