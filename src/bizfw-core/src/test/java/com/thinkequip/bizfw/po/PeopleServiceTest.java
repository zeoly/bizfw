package com.thinkequip.bizfw.po;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkequip.bizfw.base.BaseTest;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.StringUtils;
import com.thinkequip.bizfw.base.consts.ErrorCode;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.bizfw.po.service.PeopleService;

public class PeopleServiceTest extends BaseTest {

	@Autowired
	private PeopleService peopleService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testAddPeopleExists() throws BizfwServiceException {
		expectedException.expect(BizfwServiceException.class);
		expectedException.expectMessage(ErrorCode.PeopleDept.People.ADD_FAIL_EXISTED);

		People people = new People("test");
		people.setCode("zengyongli");
		people.setName("曾永理");
		people.setDepartmentId("0");
		peopleService.addPeople(people);
	}

	@Test
	public void testAddPeopleDeptErr() throws BizfwServiceException {
		expectedException.expect(BizfwServiceException.class);
		expectedException.expectMessage(ErrorCode.PeopleDept.People.ADD_FAIL_WITHOUT_DEPT);

		People people = new People("test");
		people.setCode("testadd");
		people.setName("testadd");
		people.setDepartmentId("0");
		peopleService.addPeople(people);
	}

	@Test
	public void testAddPeople() throws BizfwServiceException {
		People people = new People("test");
		people.setCode("testadd");
		people.setName("testadd");
		people.setDepartmentId("8a808087583fa7b701583faadf300000");
		String id = peopleService.addPeople(people);

		People dbPeople = peopleService.queryById(id);
		assertEquals(dbPeople.getCode(), "testadd");
	}

	@Test
	public void testModifyPeople() throws BizfwServiceException {
		People people = peopleService.getByCode("zengyongli");
		people.setName("testmodify");
		peopleService.modifyPeople(people);

		People dbPeople = peopleService.getByCode("zengyongli");
		assertEquals(dbPeople.getName(), "testmodify");
	}

	@Test
	public void testDeletePeopleDelSelf() throws BizfwServiceException {
		expectedException.expect(BizfwServiceException.class);
		expectedException.expectMessage(ErrorCode.PeopleDept.People.DEL_FAIL_SELF);

		People people = peopleService.getByCode("zengyongli");
		people.setUpdateBy("zengyongli");
		peopleService.deletePeople(people);
	}

	@Test
	public void testDeletePeople() throws BizfwServiceException {
		People people = peopleService.getByCode("zengyongli");
		people.setUpdateBy("testdelete");
		peopleService.deletePeople(people);

		People dbPeople = peopleService.getByCode("zengyongli");
		assertEquals(dbPeople, null);
	}

	@Test
	public void testGetPeopleListByDepartment() throws BizfwServiceException {
		List<People> list = peopleService.getPeopleListByDepartment("8a808087583fa7b701583faadf300000");
		assertEquals(list.size(), 3);
	}

	@Test
	public void testUnlockFail() throws BizfwServiceException {
		expectedException.expect(BizfwServiceException.class);
		expectedException.expectMessage(ErrorCode.PeopleDept.People.UNLOCK_FAIL_STATUS_ERR);

		People people = peopleService.getByCode("admin");
		peopleService.unlock(people);
	}

	@Test
	public void testUnlock() throws BizfwServiceException {
		People people = peopleService.getByCode("1232");
		peopleService.unlock(people);

		assertEquals(people.getStatus(), People.STATUS_NORMAL);
	}

	@Test
	public void testModifyPasswordFail() throws BizfwServiceException {
		expectedException.expect(BizfwServiceException.class);
		expectedException.expectMessage(ErrorCode.PeopleDept.People.UPDATE_FAIL_PWD_ERR);

		People people = peopleService.getByCode("admin");
		peopleService.modifyPassword(people, "666666", "777777");
	}

	@Test
	public void testModifyPassword() throws BizfwServiceException {
		String md5 = StringUtils.encryptMD5("777777");
		People people = peopleService.getByCode("admin");
		peopleService.modifyPassword(people, "888888", "777777");

		People dbPeople = peopleService.getByCode("admin");
		assertEquals(md5, dbPeople.getPassword());
	}
}
