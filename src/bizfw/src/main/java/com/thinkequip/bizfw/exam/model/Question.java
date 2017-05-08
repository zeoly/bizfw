package com.thinkequip.bizfw.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 问题模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Entity
@Table(name = "bf_question")
public class Question extends BaseModel {

	private static final long serialVersionUID = 7085771306496751770L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_question")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfQuestion;

	/** 所属科目id */
	@Column(name = "id_bf_subject")
	private String subjectId;

	/** 问题体 */
	@Column(name = "question_body")
	private String questionBody;

	/** 答案 */
	@Column(name = "answer")
	private String answer;

	/** 类型 */
	@Column(name = "type")
	private String type;

	public Question(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfQuestion() {
		return idBfQuestion;
	}

	public void setIdBfQuestion(String idBfQuestion) {
		this.idBfQuestion = idBfQuestion;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
