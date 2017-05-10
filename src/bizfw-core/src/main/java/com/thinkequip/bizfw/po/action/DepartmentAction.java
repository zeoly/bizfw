package com.thinkequip.bizfw.po.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.po.model.Department;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.bizfw.po.service.DepartmentService;

@Controller
@RequestMapping("/departmentAction")
public class DepartmentAction extends BaseAction {

	@Autowired
	private DepartmentService departmentService;

	@ResponseBody
	@RequestMapping("/addDepartment.do")
	public String addDepartment(HttpServletRequest request, Department department) throws BizfwServiceException {
		People people = getLoginPeople(request);
		department.init(people.getCode());
		departmentService.addDepartment(department);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/modifyDepartment.do")
	public String modifyDepartment(HttpServletRequest request, Department department) throws BizfwServiceException {
		People people = getLoginPeople(request);
		department.update(people.getCode());
		departmentService.modifyDepartment(department);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/deleteDepartment.do")
	public String deleteDepartment(HttpServletRequest request, String departmentId) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Department department = departmentService.queryById(departmentId);
		department.update(people.getCode());
		departmentService.deleteDepartment(department);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getDepartmentTree.do")
	public Department getDepartmentTree(HttpServletRequest request) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Department loginDepartment = departmentService.queryById(people.getDepartmentId());
		Department department = departmentService.getDepartmentTreeByDepartmentId(loginDepartment.getIdBfDepartment());
		return department;
	}

}
