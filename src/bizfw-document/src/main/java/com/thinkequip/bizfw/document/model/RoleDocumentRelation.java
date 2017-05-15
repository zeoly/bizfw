package com.thinkequip.bizfw.document.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 角色对应文档权限关系模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Entity
@Table(name = "bf_role_document_rel")
public class RoleDocumentRelation extends BaseModel {

	private static final long serialVersionUID = 3122928795008437565L;

	public static final String COLUMN_ROLE_ID = "idBfRole";

	public static final String COLUMN_DOCUMENT_ID = "idBfDocument";

	public static final String COLUMN_AUTH = "auth";

	/** 主键 */
	@Id
	@Column(name = "id_bf_role_document_rel")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfRoleDocumentRel;

	/** 角色id */
	@Column(name = "id_bf_role")
	private String idBfRole;

	/** 文档id */
	@Column(name = "id_bf_document")
	private String idBfDocument;

	/** 权限 */
	@Column(name = "auth")
	private String auth;

	public RoleDocumentRelation() {
		super();
	}

	public RoleDocumentRelation(String peopleCode) {
		super(peopleCode);
	}

	public RoleDocumentRelation(String peopleCode, String roleId, String documentId) {
		super(peopleCode);
		this.idBfRole = roleId;
		this.idBfDocument = documentId;
	}

	public String getIdBfRoleDocumentRel() {
		return idBfRoleDocumentRel;
	}

	public void setIdBfRoleDocumentRel(String idBfRoleDocumentRel) {
		this.idBfRoleDocumentRel = idBfRoleDocumentRel;
	}

	public String getIdBfRole() {
		return idBfRole;
	}

	public void setIdBfRole(String idBfRole) {
		this.idBfRole = idBfRole;
	}

	public String getIdBfDocument() {
		return idBfDocument;
	}

	public void setIdBfDocument(String idBfDocument) {
		this.idBfDocument = idBfDocument;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

}
