package com.thinkequip.exam;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.exam.model.Question;
import com.thinkequip.exam.service.QuestionService;

public class QuestionServiceTest extends BaseTest {

	@Autowired
	private QuestionService questionService;

	@Test
	public void testAddQuestion() throws BizfwServiceException {
		Question question = new Question("unitTest");
		question.setSubjectId("402893e35c61add1015c61adda070000");
		question.setQuestionBody("asdfasdf");
		question.setAnswer("A");
		question.setType(Question.TYPE_SELECT);
		question.setDifficulty(Question.DIFFICULTY_MEDIUM);
		String id = questionService.addQuestion(question);

		Question dbQuestion = questionService.queryById(id);
		assertEquals("402893e35c61add1015c61adda070000", dbQuestion.getSubjectId());
	}

	@Test
	public void testGetQuestionsBySubjectId() throws BizfwServiceException {
		List<Question> list = questionService.getQuestionsBySubjectId("402893e35c61add1015c61adda070000");
		assertEquals(list.size(), 2);
	}
}
