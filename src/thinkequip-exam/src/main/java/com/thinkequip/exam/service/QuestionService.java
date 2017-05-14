package com.thinkequip.exam.service;

import java.util.List;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.exam.model.Question;

/**
 * 问题服务
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月9日
 */
public interface QuestionService extends BaseService<Question> {

	public List<Question> getQuestionsBySubjectId() throws BizfwServiceException;

	public void addQuestion(Question question) throws BizfwServiceException;

}
