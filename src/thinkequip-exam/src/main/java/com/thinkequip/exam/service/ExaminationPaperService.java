package com.thinkequip.exam.service;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.exam.model.ExaminationPaper;
import com.thinkequip.exam.model.PaperQuestion;

/**
 * 试卷服务
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月9日
 */
public interface ExaminationPaperService extends BaseService<ExaminationPaper> {

	public void addExaminationPaper(ExaminationPaper examinationPaper) throws BizfwServiceException;

	public void addQuestion(PaperQuestion paperQuestion) throws BizfwServiceException;

}
