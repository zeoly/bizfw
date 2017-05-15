package com.thinkequip.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.exam.dao.ExaminationPaperDao;
import com.thinkequip.exam.dao.PaperQuestionDao;
import com.thinkequip.exam.model.ExaminationPaper;
import com.thinkequip.exam.model.PaperQuestion;
import com.thinkequip.exam.service.ExaminationPaperService;

/**
 * 试卷服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月9日
 */
@Service
public class ExaminationPaperServiceImpl extends BaseServiceImpl<ExaminationPaper> implements ExaminationPaperService {

	@Autowired
	private ExaminationPaperDao examinationPaperDao;

	@Autowired
	private PaperQuestionDao paperQuestionDao;

	@Override
	public BaseDao<ExaminationPaper> getBaseDao() {
		return examinationPaperDao;
	}

	@Override
	public String addExaminationPaper(ExaminationPaper examinationPaper) throws BizfwServiceException {
		return save(examinationPaper);
	}

	@Override
	public String addQuestion(PaperQuestion paperQuestion) throws BizfwServiceException {
		return paperQuestionDao.save(paperQuestion);
	}

}
