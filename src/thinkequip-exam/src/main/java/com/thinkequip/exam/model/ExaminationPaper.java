package com.thinkequip.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 试卷模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Entity
@Table(name = "bf_exam_paper")
public class ExaminationPaper extends BaseModel {

	private static final long serialVersionUID = -6619159987548628109L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_exam_paper")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfExaminationPaper;

	/** 所属科目id */
	@Column(name = "id_bf_subject")
	private String subjectId;

	/** 试卷总分 */
	@Column(name = "total_score")
	private Integer totalScore;

	/** 试题数量 */
	@Column(name = "question_quantity")
	private Integer questionQuantity;

	/** 试卷难度 */
	@Column(name = "difficulty")
	private String difficulty;

	/** 试卷生成策略 */
	@Column(name = "generate_type")
	private String generateType;

	public ExaminationPaper(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfExaminationPaper() {
		return idBfExaminationPaper;
	}

	public void setIdBfExaminationPaper(String idBfExaminationPaper) {
		this.idBfExaminationPaper = idBfExaminationPaper;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getGenerateType() {
		return generateType;
	}

	public void setGenerateType(String generateType) {
		this.generateType = generateType;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getQuestionQuantity() {
		return questionQuantity;
	}

	public void setQuestionQuantity(Integer questionQuantity) {
		this.questionQuantity = questionQuantity;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

}
