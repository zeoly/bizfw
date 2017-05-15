package com.thinkequip.exam.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.exam.dao.QuestionDao;
import com.thinkequip.exam.model.Question;
import com.thinkequip.exam.service.QuestionService;

/**
 * 问题服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月9日
 */
@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	@Override
	public BaseDao<Question> getBaseDao() {
		return questionDao;
	}

	@Override
	public List<Question> getQuestionsBySubjectId(String subjectId) throws BizfwServiceException {
		List<Question> list = queryByFieldAndValue(Question.COLUMN_SUBJECT_ID, subjectId);
		return list;
	}

	@Override
	public String addQuestion(Question question) throws BizfwServiceException {
		String id = save(question);
		return id;
	}

}
