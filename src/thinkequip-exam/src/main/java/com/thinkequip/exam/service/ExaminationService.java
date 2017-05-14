package com.thinkequip.exam.service;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.exam.model.Examination;

/**
 * 考试服务
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月9日
 */
public interface ExaminationService extends BaseService<Examination> {

	public void addExamination(Examination examination) throws BizfwServiceException;

}
