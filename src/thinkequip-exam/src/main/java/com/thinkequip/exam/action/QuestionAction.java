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
import com.thinkequip.exam.model.Question;
import com.thinkequip.exam.service.QuestionService;

/**
 * 题目action
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月15日
 */
@Controller
@RequestMapping("/questionAction")
public class QuestionAction extends BaseAction {

	@Autowired
	private QuestionService questionService;

	@ResponseBody
	@RequestMapping("/addQuestion.do")
	public void addQuestion(HttpServletRequest request, Question question) throws BizfwServiceException {
		People people = getLoginPeople(request);
		question.init(people.getCode());
		questionService.addQuestion(question);
	}

	@ResponseBody
	@RequestMapping("/getQuestionsBySubjectId.do")
	public List<Question> getQuestionsBySubjectId(String subjectId) throws BizfwServiceException {
		return questionService.getQuestionsBySubjectId(subjectId);
	}

	@ResponseBody
	@RequestMapping("/getQuestionById.do")
	public Question getQuestionById(String questionId) throws BizfwServiceException {
		return questionService.queryById(questionId);
	}

	@ResponseBody
	@RequestMapping
	public void modifyQuestion(HttpServletRequest request, Question question) throws BizfwServiceException {

	}
}
