package com.thinkequip.exam;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.exam.model.Subject;
import com.thinkequip.exam.service.SubjectService;

public class SubjectServiceTest extends BaseTest {

	@Autowired
	private SubjectService subjectService;

	@Test
	public void testGetAllSubject() throws BizfwServiceException {
		List<Subject> list = subjectService.getAllSubject();
		assertEquals(list.size(), 3);
	}

	@Test
	public void testAddSubject() throws BizfwServiceException {
		Subject subject = new Subject("unitTest");
		subject.setName("testAdd");
		String id = subjectService.addSubject(subject);

		Subject dbSubject = subjectService.queryById(id);
		assertEquals("testAdd", dbSubject.getName());
	}
}
