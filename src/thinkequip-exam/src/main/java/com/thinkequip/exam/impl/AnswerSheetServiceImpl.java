package com.thinkequip.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.exam.dao.AnswerSheetDao;
import com.thinkequip.exam.model.AnswerSheet;
import com.thinkequip.exam.service.AnswerSheetService;

/**
 * 答题卡服务
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月9日
 */
@Service
public class AnswerSheetServiceImpl extends BaseServiceImpl<AnswerSheet> implements AnswerSheetService {

	@Autowired
	private AnswerSheetDao answerSheetDao;

	@Override
	public BaseDao<AnswerSheet> getBaseDao() {
		return answerSheetDao;
	}

}
