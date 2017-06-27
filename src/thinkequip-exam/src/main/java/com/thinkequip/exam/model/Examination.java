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

	/** 所属科目id */
	@Column(name = "id_bf_subject")
	private String subjectId;

	/** 考试名称 */
	@Column(name = "name")
	private String name;

	/** 开始时间 */
	@Column(name = "start_date")
	private Date startDate;

	/** 结束时间 */
	@Column(name = "end_date")
	private Date endDate;

	/** 及格分数 */
	@Column(name = "pass_score")
	private Integer passScore;

	/** 参加考试次数上限 */
	@Column(name = "attend_limit")
	private Integer attendLimit;

	/** 考试时间限制（单位：分钟） */
	@Column(name = "time_limit")
	private Integer timeLimit;

	/** 待考人数 */
	@Column(name = "candidates_quantity")
	private Integer candidatesQuantity;

	/** 参加考试人数 */
	@Column(name = "examinee_quantity")
	private Integer examineeQuantity;

	public Examination() {
		super();
	}

	public Examination(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfExamination() {
		return idBfExamination;
	}

	public void setIdBfExamination(String idBfExamination) {
		this.idBfExamination = idBfExamination;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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

	public Integer getPassScore() {
		return passScore;
	}

	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}

	public Integer getAttendLimit() {
		return attendLimit;
	}

	public void setAttendLimit(Integer attendLimit) {
		this.attendLimit = attendLimit;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getCandidatesQuantity() {
		return candidatesQuantity;
	}

	public void setCandidatesQuantity(Integer candidatesQuantity) {
		this.candidatesQuantity = candidatesQuantity;
	}

	public Integer getExamineeQuantity() {
		return examineeQuantity;
	}

	public void setExamineeQuantity(Integer examineeQuantity) {
		this.examineeQuantity = examineeQuantity;
	}

}
