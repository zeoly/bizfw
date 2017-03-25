package com.thinkequip.bizfw.document;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkequip.bizfw.base.BaseTest;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.service.DocumentService;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.bizfw.po.service.PeopleService;

public class DocumentServiceTest extends BaseTest {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private PeopleService peopleService;

	@Test
	public void testGetAuthFolderTree() {
		try {
			People people = peopleService.queryById("8a80808659592fc50159593052ef0000");
			Document document = documentService.getAuthFolderTree(people);
			printDocument(document);
		} catch (BizfwServiceException e) {
			System.out.println(e.getErrorMsg());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void printDocument(Document document) {
		System.out.println(document.getName());
		if (ListUtils.isNotEmpty(document.getChildList())) {
			for (Document d : document.getChildList()) {
				printDocument(d);
			}
		}
	}
}
