package com.thinkequip.bizfw.auth.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.auth.service.RoleService;
import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.po.model.People;

@Controller
@RequestMapping("/roleAction")
public class RoleAction extends BaseAction {

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping("/getRoleList.do")
	public List<Role> getRoleList() throws BizfwServiceException {
		return roleService.getAllRoleList();
	}

	@ResponseBody
	@RequestMapping("/addRole.do")
	public String addRole(HttpServletRequest request, Role role) throws BizfwServiceException {
		People people = getLoginPeople(request);
		role.init(people.getCode());
		roleService.addRole(role);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/modifyRole.do")
	public String modifyRole(HttpServletRequest request, Role role) throws BizfwServiceException {
		People people = getLoginPeople(request);
		role.update(people.getCode());
		roleService.modify(role);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/deleteRole.do")
	public String deleteRole(String roleId) throws BizfwServiceException {
		roleService.deleteRole(roleId);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getRoleOfPeople.do")
	public List<Role> getRoleOfPeople(HttpServletRequest request, String peopleId) throws BizfwServiceException {
		List<Role> roleList = roleService.getRoleListByPeople(peopleId);
		return roleList;
	}
}
