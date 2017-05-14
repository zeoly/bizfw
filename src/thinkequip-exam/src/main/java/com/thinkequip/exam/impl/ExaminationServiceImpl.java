package com.thinkequip.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.exam.dao.ExaminationDao;
import com.thinkequip.exam.dao.ExaminationRoleRelationDao;
import com.thinkequip.exam.model.Examination;
import com.thinkequip.exam.service.ExaminationService;

/**
 * 考试服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月9日
 */
@Service
public class ExaminationServiceImpl extends BaseServiceImpl<Examination> implements ExaminationService {

	@Autowired
	private ExaminationDao examinationDao;

	@Autowired
	private ExaminationRoleRelationDao examinationRoleRelationDao;

	@Override
	public BaseDao<Examination> getBaseDao() {
		return examinationDao;
	}

	@Override
	public void addExamination(Examination examination) throws BizfwServiceException {
		// TODO Auto-generated method stub

	}

}
