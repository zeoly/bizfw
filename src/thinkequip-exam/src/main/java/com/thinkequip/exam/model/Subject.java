package com.thinkequip.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 科目模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Entity
@Table(name = "bf_subject")
public class Subject extends BaseModel {

	private static final long serialVersionUID = -9152776972927305335L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_subject")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfSubject;

	/** 科目名 */
	@Column(name = "name")
	private String name;

	/** 父科目id */
	@Column(name = "parent_id")
	private String parentId;

	public Subject(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfSubject() {
		return idBfSubject;
	}

	public void setIdBfSubject(String idBfSubject) {
		this.idBfSubject = idBfSubject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
