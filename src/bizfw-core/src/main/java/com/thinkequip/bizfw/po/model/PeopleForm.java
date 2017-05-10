package com.thinkequip.bizfw.po.model;

import java.util.List;

public class PeopleForm {

	private People people;

	private List<String> roleIdList;

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}
}
