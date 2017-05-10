package com.thinkequip.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
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

}
