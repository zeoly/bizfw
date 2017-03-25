package com.thinkequip.bizfw.document.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.auth.service.RoleService;
import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.model.RoleDocumentRelation;
import com.thinkequip.bizfw.document.service.DocumentService;
import com.thinkequip.bizfw.po.model.People;

@Controller
@RequestMapping("/folderAction")
public class FolderAction extends BaseAction {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping("/addFolder.do")
	public String addFolder(HttpServletRequest request, Document document) throws BizfwServiceException {
		People people = getLoginPeople(request);
		document.init(people.getCode());
		documentService.addFolder(document);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/modifyFolder.do")
	public String modifyFolder(HttpServletRequest request, Document document) throws BizfwServiceException {
		People people = getLoginPeople(request);
		document.update(people.getCode());
		documentService.modifyFolder(document);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/deleteFolder.do")
	public String deleteFolder(String documentId) throws BizfwServiceException {
		documentService.deleteFolder(documentId);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/setFolderOfRole.do")
	public String setFolderOfRole(HttpServletRequest request, String roleId, List<String> folderIdList)
			throws BizfwServiceException {
		People people = getLoginPeople(request);
		Role role = roleService.queryById(roleId);
		role.update(people.getCode());
		documentService.setFolderOfRole(role, folderIdList);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/setRoleOfFolder.do")
	public String setRoleOfFolder(HttpServletRequest request, String folderId,
			@RequestParam(value = "roleIdList[]") List<String> roleIdList) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Document folder = documentService.queryById(folderId);
		folder.update(people.getCode());
		documentService.setRoleOfFolder(folder, roleIdList);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/setAuth.do")
	public String setAuth(HttpServletRequest request, String relationId, String auth) throws BizfwServiceException {
		People people = getLoginPeople(request);
		RoleDocumentRelation relation = documentService.getRelationById(relationId);
		relation.update(people.getCode());
		relation.setAuth(auth);
		documentService.modifyRelation(relation);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getAllFolderTree.do")
	public Document getAllFolderTree() throws BizfwServiceException {
		Document rootFolder = documentService.getAllFolderTree();
		return rootFolder;
	}

	@ResponseBody
	@RequestMapping("/getAuthFolderTree.do")
	public Document getAuthFolderTree() throws BizfwServiceException {
		return null;
	}

	@ResponseBody
	@RequestMapping("/getFolderOfRole.do")
	public List<Document> getFolderOfRole(String roleId) throws BizfwServiceException {
		Role role = roleService.queryById(roleId);
		List<Document> folderList = documentService.getFolderOfRole(role);
		return folderList;
	}

	@ResponseBody
	@RequestMapping("/getRoleOfFolder.do")
	public List<Role> getRoleOfFolder(String folderId) throws BizfwServiceException {
		Document folder = documentService.queryById(folderId);
		List<Role> roleList = documentService.getRoleOfFolder(folder);
		return roleList;
	}
}
