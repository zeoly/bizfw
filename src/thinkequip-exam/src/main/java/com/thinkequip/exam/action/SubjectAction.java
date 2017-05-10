package com.thinkequip.exam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.exam.model.Subject;
import com.thinkequip.exam.service.SubjectService;

/**
 * 考试科目action
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月8日
 */
@Controller
@RequestMapping("/subjectAction")
public class SubjectAction extends BaseAction {

	@Autowired
	private SubjectService subjectService;

	@ResponseBody
	@RequestMapping("/getAllSubject")
	public List<Subject> getAllSubject() throws BizfwServiceException {
		return subjectService.getAllSubject();
	}

	@ResponseBody
	@RequestMapping("/addSubject.do")
	public void addSubject(HttpServletRequest request, Subject subject) throws BizfwServiceException {

	}

	@ResponseBody
	@RequestMapping("/deleteSubject.do")
	public void deleteSubject(HttpServletRequest request, String subjectId) throws BizfwServiceException {

	}
}
