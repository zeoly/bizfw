package com.thinkequip.exam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.exam.model.Examination;
import com.thinkequip.exam.model.ExaminationPaper;
import com.thinkequip.exam.service.ExaminationPaperService;
import com.thinkequip.exam.service.ExaminationService;

/**
 * 考试action
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月15日
 */
@Controller
@RequestMapping("/examinationAction")
public class ExaminationAction extends BaseAction {

	@Autowired
	private ExaminationService examinationService;

	@Autowired
	private ExaminationPaperService examinationPaperService;

	@ResponseBody
	@RequestMapping("/addExamination.do")
	public void addExamination(HttpServletRequest request, Examination examination) throws BizfwServiceException {
		People people = getLoginPeople(request);
		examination.init(people.getCode());
		examinationService.addExamination(examination);
	}

	@ResponseBody
	@RequestMapping("/setRoleOfExamination.do")
	public void setRoleOfExamination(HttpServletRequest request, String examinationId, List<String> roleIdList)
			throws BizfwServiceException {
		People people = getLoginPeople(request);
		Examination examination = examinationService.queryById(examinationId);
		examination.update(people.getCode());
		examinationService.setRoleOfExamination(examination, roleIdList);
	}

	@ResponseBody
	@RequestMapping("/addExaminationPaper.do")
	public void addExaminationPaper(HttpServletRequest request, ExaminationPaper examinationPaper)
			throws BizfwServiceException {
		People people = getLoginPeople(request);
		examinationPaper.init(people.getCode());
		examinationPaperService.addExaminationPaper(examinationPaper);
	}

}
