package com.thinkequip.bizfw.po;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkequip.bizfw.base.BaseTest;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.consts.ErrorCode;
import com.thinkequip.bizfw.po.model.Department;
import com.thinkequip.bizfw.po.service.DepartmentService;

public class DepartmentServiceTest extends BaseTest {

	@Autowired
	private DepartmentService departmentService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testAddDepartment() throws BizfwServiceException {
		Department department = new Department("test");
		department.setCode("test");
		department.setName("测试");
		Department parentDepartment = departmentService.queryByCode("root");
		department.setParentDepartmentId(parentDepartment.getIdBfDepartment());
		departmentService.addDepartment(department);

		Department dbDepartment = departmentService.queryByCode("test");
		assertEquals("测试", dbDepartment.getName());
	}

	@Test
	public void testModifyDepartment() throws BizfwServiceException {
		Department department = departmentService.queryByCode("root");
		department.setName("测试修改");
		departmentService.update(department);

		Department dbDepartment = departmentService.queryByCode("root");
		assertEquals("测试修改", dbDepartment.getName());
	}

	@Test
	public void testDeleteDepartmentHasChild() throws BizfwServiceException {
		expectedException.expect(BizfwServiceException.class);
		expectedException.expectMessage(ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_CHILD);

		Department department = departmentService.queryByCode("root");
		departmentService.deleteDepartment(department);
	}

	@Test
	public void testDeleteDepartmentHasPeople() throws BizfwServiceException {
		expectedException.expect(BizfwServiceException.class);
		expectedException.expectMessage(ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_CHILD);

		Department department = departmentService.queryByCode("root");
		departmentService.deleteDepartment(department);

		Department dbDepartment = departmentService.queryByCode("root");
		assertNull(dbDepartment);
	}

	// @Test
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
