package com.thinkequip.bizfw.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.exam.dao.ExaminationPaperDao;
import com.thinkequip.bizfw.exam.model.ExaminationPaper;
import com.thinkequip.bizfw.exam.service.ExaminationPaperService;

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

	@Override
	public BaseDao<ExaminationPaper> getBaseDao() {
		return examinationPaperDao;
	}

}
