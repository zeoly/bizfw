package com.thinkequip.bizfw.po;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

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
		departmentService.modifyDepartment(department);

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
		expectedException.expectMessage(ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_PEOPLE);

		Department department = departmentService.queryByCode("11");
		departmentService.deleteDepartment(department);
	}

	@Test
	public void testDeleteDepartment() throws BizfwServiceException {
		Department department = departmentService.queryByCode("211");
		departmentService.deleteDepartment(department);

		Department dbDepartment = departmentService.queryByCode("211");
		assertNull(dbDepartment);
	}

	@Test
	public void testGetParentDepartment() throws BizfwServiceException {
		Department department = departmentService.queryByCode("1");
		Department parentDepartment = departmentService.getParentDepartment(department);
		assertEquals("root", parentDepartment.getName());
	}

	@Test
	public void testGetChildDepartmentList() throws BizfwServiceException {
		Department department = departmentService.queryByCode("root");
		List<Department> list = departmentService.getChildDepartmentList(department.getIdBfDepartment());
		assertEquals(list.size(), 3);
	}

	@Test
	public void testGetAllParentDeptmentList() throws BizfwServiceException {
		Department department = departmentService.queryByCode("1");
		List<Department> list = departmentService.getAllParentDeptmentList(department.getIdBfDepartment());
		assertEquals(list.size(), 1);
	}

	@Test
	public void testGetAllChildDeptmentList() throws BizfwServiceException {
		Department department = departmentService.queryByCode("1");
		List<Department> list = departmentService.getAllChildDeptmentList(department.getIdBfDepartment());
		assertEquals(list.size(), 3);
	}

	@Test
	public void testGetDepartmentTreeByDepartmentId() throws BizfwServiceException {
		Department department = departmentService.queryByCode("1");
		Department node = departmentService.getDepartmentTreeByDepartmentId(department.getIdBfDepartment());
		assertEquals(node.getChildDepartmentList().size(), 2);
	}
}
