package com.thinkequip.bizfw.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 角色菜单关联模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Entity
@Table(name = "bf_role_menu_rel")
public class RoleMenuRelation extends BaseModel {

	private static final long serialVersionUID = -4727978116878032279L;

	public static final String COLUMN_ROLE_ID = "roleId";

	public static final String COLUMN_MENU_ID = "menuId";

	/** 主键 */
	@Id
	@Column(name = "id_bf_role_menu_rel")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	private String idBfRoleMenuRel;

	/** 角色id */
	@Column(name = "id_bf_role")
	private String roleId;

	/** 菜单id */
	@Column(name = "id_bf_menu")
	private String menuId;

	public RoleMenuRelation(String peopleCode, String roleId, String menuId) {
		super(peopleCode);
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public RoleMenuRelation() {
		super();
	}

	public String getIdBfRoleMenuRel() {
		return idBfRoleMenuRel;
	}

	public void setIdBfRoleMenuRel(String idBfRoleMenuRel) {
		this.idBfRoleMenuRel = idBfRoleMenuRel;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
