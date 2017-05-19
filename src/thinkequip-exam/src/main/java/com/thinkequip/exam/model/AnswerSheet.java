package com.thinkequip.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/***
 * 答题卡模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Entity
@Table(name = "bf_answer_sheet")
public class AnswerSheet extends BaseModel {

	private static final long serialVersionUID = -2505606192321181104L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_answer_sheet")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfAnswerSheet;

	/** 对应试卷id */
	@Column(name = "id_bf_exam_paper")
	private String examinationPaperId;

	/** 考生人员代码 */
	@Column(name = "examinee_code")
	private String examineeCode;

	/** 答题卡分数 */
	@Column(name = "score")
	private Integer score;

	/** 考试耗时（单位：分钟） */
	@Column(name = "exam_time")
	private Integer examTime;

	/** 答题次数 */
	@Column(name = "attend_times")
	private Integer attendTimes;

	public AnswerSheet(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfAnswerSheet() {
		return idBfAnswerSheet;
	}

	public void setIdBfAnswerSheet(String idBfAnswerSheet) {
		this.idBfAnswerSheet = idBfAnswerSheet;
	}

	public String getExaminationPaperId() {
		return examinationPaperId;
	}

	public void setExaminationPaperId(String examinationPaperId) {
		this.examinationPaperId = examinationPaperId;
	}

	public String getExamineeCode() {
		return examineeCode;
	}

	public void setExamineeCode(String examineeCode) {
		this.examineeCode = examineeCode;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getExamTime() {
		return examTime;
	}

	public void setExamTime(Integer examTime) {
		this.examTime = examTime;
	}

	public Integer getAttendTimes() {
		return attendTimes;
	}

	public void setAttendTimes(Integer attendTimes) {
		this.attendTimes = attendTimes;
	}

}
