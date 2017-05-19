package com.thinkequip.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 试卷问题
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Entity
@Table(name = "bf_paper_question")
public class PaperQuestion extends BaseModel {

	private static final long serialVersionUID = 1989735737484878248L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_paper_question")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfPaperQuestion;

	/** 对应试卷id */
	@Column(name = "id_bf_exam_paper")
	private String examinationPaperId;

	/** 试题编号 */
	@Column(name = "orders")
	private Integer orders;

	/** 类型 */
	@Column(name = "type")
	private String type;

	/** 对应问题id */
	@Column(name = "id_bf_question")
	private String questionId;

	/** 试题体 */
	@Column(name = "question_body")
	private String questionBody;

	/** 试题答案 */
	@Column(name = "answer")
	private String answer;

	/** 试题分数 */
	@Column(name = "score")
	private Integer score;

	public PaperQuestion(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfPaperQuestion() {
		return idBfPaperQuestion;
	}

	public void setIdBfPaperQuestion(String idBfPaperQuestion) {
		this.idBfPaperQuestion = idBfPaperQuestion;
	}

	public String getExaminationPaperId() {
		return examinationPaperId;
	}

	public void setExaminationPaperId(String examinationPaperId) {
		this.examinationPaperId = examinationPaperId;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
