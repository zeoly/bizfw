package com.thinkequip.bizfw.po.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.common.PropertiesUtils;
import com.thinkequip.bizfw.base.common.StringUtils;
import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 人员模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
@Entity
@Table(name = "bf_people")
public class People extends BaseModel {

	private static final long serialVersionUID = -9173667783512729829L;

	public static final String COLUMN_CODE = "code";

	public static final String COLUMN_DEPARTMENT_ID = "departmentId";

	public static final String STATUS_INVALID = "0";

	public static final String STATUS_NORMAL = "1";

	public static final String STATUS_LOCKED = "2";

	public static final String STATUS_UNCHECK = "3";

	/** 主键 */
	@Id
	@Column(name = "id_bf_people")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	private String idBfPeople;

	/** 用户名 */
	@Column(name = "code")
	private String code;

	/** 用户姓名 */
	@Column(name = "name")
	private String name;

	/** 密码 */
	@Column(name = "password")
	private String password;

	/** 机构id */
	@Column(name = "id_bf_department")
	private String departmentId;

	/** 状态 */
	@Column(name = "status")
	private String status;

	/** 密码错误次数 */
	@Column(name = "error_count")
	private int errorCount;

	public People() {
		super();
	}

	public People(String peopleCode) {
		super(peopleCode);
		this.errorCount = 0;
		this.status = STATUS_NORMAL;
		this.password = StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd"));
	}

	public String getIdBfPeople() {
		return idBfPeople;
	}

	public void setIdBfPeople(String idBfPeople) {
		this.idBfPeople = idBfPeople;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
