package com.thinkequip.exam.service;

import java.util.List;

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

	/**
	 * 新增考试
	 * 
	 * @param examination
	 *            考试模型
	 * @return 主键
	 * @throws BizfwServiceException
	 */
	public String addExamination(Examination examination) throws BizfwServiceException;

	/**
	 * 设置可参与考试的角色
	 * 
	 * @param examination
	 *            考试
	 * @param roleIdList
	 *            角色id列表
	 * @throws BizfwServiceException
	 */
	public void setRoleOfExamination(Examination examination, List<String> roleIdList) throws BizfwServiceException;

}
