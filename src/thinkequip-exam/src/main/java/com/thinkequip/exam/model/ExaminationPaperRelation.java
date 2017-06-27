package com.thinkequip.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 考试与试卷关联关系模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月19日
 */
@Entity
@Table(name = "bf_exam_paper_rel")
public class ExaminationPaperRelation extends BaseModel {

	private static final long serialVersionUID = 5579823582123189916L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_exam_paper_rel")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfExamPaperRel;

	/** 考试id */
	@Column(name = "id_bf_examination")
	private String examinationId;

	/** 试卷id */
	@Column(name = "id_bf_exam_paper")
	private String paperId;

	public ExaminationPaperRelation() {
		super();
	}

	public ExaminationPaperRelation(String peopleCode) {
		super(peopleCode);
	}

	public ExaminationPaperRelation(String peopleCode, String examinationId, String paperId) {
		super(peopleCode);
		this.examinationId = examinationId;
		this.paperId = paperId;
	}

	public String getIdBfExamPaperRel() {
		return idBfExamPaperRel;
	}

	public void setIdBfExamPaperRel(String idBfExamPaperRel) {
		this.idBfExamPaperRel = idBfExamPaperRel;
	}

	public String getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(String examinationId) {
		this.examinationId = examinationId;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

}
