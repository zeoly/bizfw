package com.thinkequip.exam.model;

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

	public static final String COLUMN_SUBJECT_ID = "subjectId";

	public static final String TYPE_SELECT = "0";

	public static final String TYPE_MULTI_SELECT = "1";

	public static final String TYPE_FILL = "2";
	
	public static final String DIFFICULTY_EASY = "0";
	
	public static final String DIFFICULTY_MEDIUM = "1";
	
	public static final String DIFFICULTY_HARD = "2";

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

	/** 难度 */
	@Column(name = "difficulty")
	private String difficulty;

	public Question() {
		super();
	}

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

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

}
