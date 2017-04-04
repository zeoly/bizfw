package com.thinkequip.bizfw.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 人员角色关系模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Entity
@Table(name = "bf_people_role_rel")
public class PeopleRoleRelation extends BaseModel {

	private static final long serialVersionUID = -5402400327267119179L;

	public static final String COLUMN_PEOPLE_ID = "peopleId";

	public static final String COLUMN_ROLE_ID = "roleId";

	/** 主键 */
	@Id
	@Column(name = "id_bf_people_role_rel")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfPeopleRoleRel;

	/** 人员id */
	@Column(name = "id_bf_people")
	private String peopleId;

	/** 角色id */
	@Column(name = "id_bf_role")
	private String roleId;

	public PeopleRoleRelation(String peopleCode, String peopleId, String roleId) {
		super(peopleCode);
		this.peopleId = peopleId;
		this.roleId = roleId;
	}

	public PeopleRoleRelation() {
		super();
	}

	public String getIdBfPeopleRoleRel() {
		return idBfPeopleRoleRel;
	}

	public void setIdBfPeopleRoleRel(String idBfPeopleRoleRel) {
		this.idBfPeopleRoleRel = idBfPeopleRoleRel;
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
