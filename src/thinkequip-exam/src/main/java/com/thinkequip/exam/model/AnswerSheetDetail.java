package com.thinkequip.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 答题卡详情模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Entity
@Table(name = "bf_an_sheet_detail")
public class AnswerSheetDetail extends BaseModel {

	private static final long serialVersionUID = -3341663104034113338L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_an_sheet_detail")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfAnswerSheetDetail;

	/** 对应答题卡id */
	@Column(name = "id_bf_answer_sheet")
	private String AnswerSheetId;

	/** 对应试卷问题id */
	@Column(name = "id_bf_paper_question")
	private String paperQuestionId;

	/** 答题卡详情答案 */
	@Column(name = "sheet_answer")
	private String sheetAnswer;

	/** 答题卡问题得分 */
	@Column(name = "score")
	private Integer score;

	public AnswerSheetDetail() {
		super();
	}

	public AnswerSheetDetail(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfAnswerSheetDetail() {
		return idBfAnswerSheetDetail;
	}

	public void setIdBfAnswerSheetDetail(String idBfAnswerSheetDetail) {
		this.idBfAnswerSheetDetail = idBfAnswerSheetDetail;
	}

	public String getAnswerSheetId() {
		return AnswerSheetId;
	}

	public void setAnswerSheetId(String answerSheetId) {
		AnswerSheetId = answerSheetId;
	}

	public String getPaperQuestionId() {
		return paperQuestionId;
	}

	public void setPaperQuestionId(String paperQuestionId) {
		this.paperQuestionId = paperQuestionId;
	}

	public String getSheetAnswer() {
		return sheetAnswer;
	}

	public void setSheetAnswer(String sheetAnswer) {
		this.sheetAnswer = sheetAnswer;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
