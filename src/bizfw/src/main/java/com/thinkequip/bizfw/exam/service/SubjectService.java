package com.thinkequip.bizfw.exam.service;

import java.util.List;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.exam.model.Subject;

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
}
