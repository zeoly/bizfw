package com.thinkequip.exam.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.exam.dao.ExaminationDao;
import com.thinkequip.exam.dao.ExaminationRoleRelationDao;
import com.thinkequip.exam.model.Examination;
import com.thinkequip.exam.model.ExaminationRoleRelation;
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
	public String addExamination(Examination examination) throws BizfwServiceException {
		return save(examination);
	}

	@Transactional
	@Override
	public void setRoleOfExamination(Examination examination, List<String> roleIdList) throws BizfwServiceException {
		examinationRoleRelationDao.deleteByFieldAndValue(ExaminationRoleRelation.COLUMN_EXAMINATION_ID,
				examination.getIdBfExamination());
		for (String roleId : roleIdList) {
			ExaminationRoleRelation relation = new ExaminationRoleRelation(examination.getUpdateBy(),
					examination.getIdBfExamination(), roleId);
			examinationRoleRelationDao.save(relation);
		}
	}

}
