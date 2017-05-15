package com.thinkequip.exam.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.exam.dao.SubjectDao;
import com.thinkequip.exam.model.Subject;
import com.thinkequip.exam.service.SubjectService;

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
	public BaseDao<Subject> getBaseDao() {
		return SubjectDao;
	}

	@Override
	public List<Subject> getAllSubject() throws BizfwServiceException {
		return list();
	}

	@Override
	public String addSubject(Subject subject) throws BizfwServiceException {
		String id = save(subject);
		return id;
	}

	@Override
	public void deleteSubject(Subject subject) throws BizfwServiceException {
		checkObjectNotNull(subject, "科目", "删除科目");
		delete(subject.getIdBfSubject());
	}

}
