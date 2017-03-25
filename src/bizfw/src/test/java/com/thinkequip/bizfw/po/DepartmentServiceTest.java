package com.thinkequip.bizfw.po;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.thinkequip.bizfw.base.BaseTest;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.po.model.Department;
import com.thinkequip.bizfw.po.service.DepartmentService;

public class DepartmentServiceTest extends BaseTest {

	@Autowired
	private DepartmentService departmentService;

	@Test
	@Rollback(false)
	public void testAddDepartment() {
		try {
			Department department = new Department("system");
			department.setCode("12");
			department.setName("测试12");
			Department parentDepartment = departmentService.queryByCode("1");
			department.setParentDepartmentId(parentDepartment.getIdBfDepartment());
			departmentService.addDepartment(department);
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void testDeleteDepartment() {
		try {
			Department department = departmentService.queryById("8a8080855896c3f1015896c3f5f00000");
			departmentService.deleteDepartment(department);
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void testGetParentDepartment() {
		try {
			Department department = departmentService.queryByCode("0");
			Department parentDepartment = departmentService.getParentDepartment(department);
			System.out.println(parentDepartment.getName());
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}

	// @Test
	@Rollback(false)
	public void test() {
		try {
			Department department = new Department("test");
			department.setLevel(Department.LEVEL_ROOT + 1);
			department.setCode("0");
			department.setName("测试root机构");
			Department parentDepartment = departmentService.queryByCode("root");
			department.setParentDepartmentId(parentDepartment.getIdBfDepartment());
			departmentService.addDepartment(department);

			department = departmentService.queryByCode("0");
			System.out.println(department.getName());
			department.setName("测试root机构1");
			departmentService.modifyDepartment(department);

			department = departmentService.queryByCode("0");
			System.out.println(department.getName());
			departmentService.deleteDepartment(department);
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		}
	}
}
