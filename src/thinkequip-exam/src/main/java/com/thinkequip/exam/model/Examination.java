package com.thinkequip.exam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 考试模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
public class Examination extends BaseModel {

	private static final long serialVersionUID = -7953840184031570362L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_examination")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfExamination;

	/** 考试名称 */
	@Column(name = "name")
	private String name;

	/** 开始时间 */
	@Column(name = "start_date")
	private Date startDate;

	/** 结束时间 */
	@Column(name = "end_date")
	private Date endDate;

	public Examination(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfExamination() {
		return idBfExamination;
	}

	public void setIdBfExamination(String idBfExamination) {
		this.idBfExamination = idBfExamination;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
