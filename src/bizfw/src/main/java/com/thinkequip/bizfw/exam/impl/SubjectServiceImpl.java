package com.thinkequip.bizfw.exam.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.exam.dao.SubjectDao;
import com.thinkequip.bizfw.exam.model.Subject;
import com.thinkequip.bizfw.exam.service.SubjectService;

/**
 * 考试科目服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Service
public class SubjectServiceImpl extends BaseServiceImpl<Subject> implements SubjectService {

	@Autowired
	private SubjectDao SubjectDao;

	@Override
	public List<Subject> getAllSubject() throws BizfwServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDao<Subject> getBaseDao() {
		return SubjectDao;
	}

}
