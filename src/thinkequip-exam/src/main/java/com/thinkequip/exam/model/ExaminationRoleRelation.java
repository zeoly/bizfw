package com.thinkequip.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 考试角色关系模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Entity
@Table(name = "bf_exam_role_rel")
public class ExaminationRoleRelation extends BaseModel {

	private static final long serialVersionUID = -3355952611322114225L;

	public static final String COLUMN_EXAMINATION_ID = "examinationId";

	/** 主键 */
	@Id
	@Column(name = "id_bf_exam_role_rel")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfExamRoleRel;

	/** 考试id */
	@Column(name = "id_bf_examination")
	private String examinationId;

	/** 角色id */
	@Column(name = "id_bf_role")
	private String roleId;

	public ExaminationRoleRelation(String peopleCode) {
		super(peopleCode);
	}

	public ExaminationRoleRelation(String peopleCode, String examinationId, String roleId) {
		super(peopleCode);
		this.examinationId = examinationId;
		this.roleId = roleId;
	}

	public String getIdBfExamRoleRel() {
		return idBfExamRoleRel;
	}

	public void setIdBfExamRoleRel(String idBfExamRoleRel) {
		this.idBfExamRoleRel = idBfExamRoleRel;
	}

	public String getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(String examinationId) {
		this.examinationId = examinationId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
