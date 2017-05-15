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

	/**
	 * 新增考卷
	 * 
	 * @param examinationPaper
	 *            考卷模型
	 * @return 主键
	 * @throws BizfwServiceException
	 */
	public String addExaminationPaper(ExaminationPaper examinationPaper) throws BizfwServiceException;

	public String addQuestion(PaperQuestion paperQuestion) throws BizfwServiceException;

}
