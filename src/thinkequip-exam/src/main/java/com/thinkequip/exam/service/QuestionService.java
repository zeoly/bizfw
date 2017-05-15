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

	/**
	 * 获取指定科目的所有问题
	 * 
	 * @param subjectId
	 *            科目id
	 * @return 问题列表
	 * @throws BizfwServiceException
	 */
	public List<Question> getQuestionsBySubjectId(String subjectId) throws BizfwServiceException;

	/**
	 * 添加问题
	 * 
	 * @param question
	 *            问题模型
	 * @return 主键
	 * @throws BizfwServiceException
	 */
	public String addQuestion(Question question) throws BizfwServiceException;

}
