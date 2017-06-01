package com.thinkequip.exam.service;

import java.util.List;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.exam.model.Subject;

/**
 * 考试科目服务
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
public interface SubjectService extends BaseService<Subject> {

	/**
	 * 获取所有考试科目
	 * 
	 * @return 科目列表
	 * @throws BizfwServiceException
	 */
	public List<Subject> getAllSubject() throws BizfwServiceException;

	/**
	 * 新增科目
	 * 
	 * @param subject
	 *            科目模型
	 * @return 主键
	 * @throws BizfwServiceException
	 */
	public String addSubject(Subject subject) throws BizfwServiceException;

	/**
	 * 删除科目
	 * 
	 * @param subject
	 *            科目模型
	 * @throws BizfwServiceException
	 */
	public void deleteSubject(Subject subject) throws BizfwServiceException;
}
