package com.thinkequip.bizfw.document.model;

import java.util.ArrayList;
import java.util.List;

import com.thinkequip.bizfw.auth.model.Role;

public class DocumentForm extends Document {

	private static final long serialVersionUID = 824958842982884886L;

	private List<Role> roleList = new ArrayList<Role>();

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
