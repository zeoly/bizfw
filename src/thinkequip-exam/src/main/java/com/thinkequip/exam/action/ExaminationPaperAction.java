package com.thinkequip.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.exam.model.ExaminationPaper;
import com.thinkequip.exam.service.ExaminationPaperService;

/**
 * 试卷action
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月21日
 */
@Controller
@RequestMapping("/examinationPaperAction")
public class ExaminationPaperAction extends BaseAction {

	@Autowired
	private ExaminationPaperService examinationPaperService;

	@ResponseBody
	@RequestMapping("/addPaper.do")
	public void addPaper(ExaminationPaper examinationPaper) throws BizfwServiceException {

	}

	@ResponseBody
	@RequestMapping()
	public void deletePaper(String paperId) throws BizfwServiceException {

	}
}
