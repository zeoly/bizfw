package com.thinkequip.bizfw.po.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 机构模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
@Entity
@Table(name = "bf_department")
public class Department extends BaseModel {

	private static final long serialVersionUID = -8679405946033789045L;

	public static final String COLUMN_CODE = "code";

	public static final String COLUMN_PARENT_DEPT_ID = "parentDepartmentId";

	public static final int LEVEL_ROOT = 0;

	/** 主键 */
	@Id
	@Column(name = "id_bf_department")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	private String idBfDepartment;

	/** 机构码 */
	@Column(name = "code")
	private String code;

	/** 机构名称 */
	@Column(name = "name")
	private String name;

	/** 机构等级 */
	@Column(name = "level")
	private Integer level;

	/** 父机构id */
	@Column(name = "parent_dept_id")
	private String parentDepartmentId;

	/** 自机构列表 */
	@Transient
	private List<Department> childDepartmentList;

	public Department() {
		super();
	}

	public Department(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfDepartment() {
		return idBfDepartment;
	}

	public void setIdBfDepartment(String idBfDepartment) {
		this.idBfDepartment = idBfDepartment;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentDepartmentId() {
		return parentDepartmentId;
	}

	public void setParentDepartmentId(String parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}

	public List<Department> getChildDepartmentList() {
		return childDepartmentList;
	}

	public void setChildDepartmentList(List<Department> childDepartmentList) {
		this.childDepartmentList = childDepartmentList;
	}
}
