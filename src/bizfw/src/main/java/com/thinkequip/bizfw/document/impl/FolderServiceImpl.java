package com.thinkequip.bizfw.document.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.auth.service.RoleService;
import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.base.common.StringUtils;
import com.thinkequip.bizfw.base.consts.ErrorCode;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.document.dao.FolderDao;
import com.thinkequip.bizfw.document.dao.RoleDocumentRelDao;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.model.DocumentOperationLog;
import com.thinkequip.bizfw.document.model.RoleDocumentRelation;
import com.thinkequip.bizfw.document.service.DocumentOptLogService;
import com.thinkequip.bizfw.document.service.FolderService;
import com.thinkequip.bizfw.po.model.People;

/**
 * 文件夹服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月21日
 */
@Service("folderService")
public class FolderServiceImpl extends BaseServiceImpl<Document> implements FolderService {

	@Autowired
	private FolderDao folderDao;

	@Autowired
	private RoleDocumentRelDao roleDocumentRelDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private DocumentOptLogService documentOptLogService;

	@Override
	public Document getAllFolderTree() throws BizfwServiceException {
		List<Document> folderList = folderDao.getAllFolder();
		ListUtils.sort(folderList, Document.COLUMN_NAME);
		Document rootFolder = folderDao.getRootFolder();
		Document folder = convertListToTree(folderList, rootFolder);
		return folder;
	}

	@Override
	public Document getAuthFolderTree(People people) throws BizfwServiceException {
		List<Document> authFolderList = new ArrayList<Document>();
		List<Role> roleList = roleService.getRoleListByPeople(people.getIdBfPeople());
		for (Role role : roleList) {
			List<Document> folderList = getFolderOfRole(role);
			for (Document folder : folderList) {
				if (!authFolderList.contains(folder)) {
					authFolderList.add(folder);
				}
			}
		}
		replenishAuthFolderList(authFolderList);
		ListUtils.sort(authFolderList, Document.COLUMN_NAME);
		Document rootFolder = folderDao.getRootFolder();
		Document folder = convertListToTree(authFolderList, rootFolder);
		return folder;
	}

	@Override
	public List<Document> getFolderOfRole(Role role) throws BizfwServiceException {
		List<Document> folderList = new ArrayList<Document>();
		List<RoleDocumentRelation> relationList = roleDocumentRelDao
				.queryByFieldAndValue(RoleDocumentRelation.COLUMN_ROLE_ID, role.getIdBfRole());
		for (RoleDocumentRelation relation : relationList) {
			Document folder = queryById(relation.getIdBfDocument());
			folderList.add(folder);
		}
		return folderList;
	}

	@Override
	public String addFolder(Document document) throws BizfwServiceException {
		Document parentDocument = queryById(document.getOwnerDocumentId());
		checkObjectNotNull(parentDocument, "文件夹[" + document.getOwnerDocumentId() + "]", "新增文件夹");
		document.setType(Document.TYPE_DIRECTORY);
		document.setPath(parentDocument.getPath() + "/" + document.getName());
		String id = save(document);
		documentOptLogService.addLog(document, DocumentOperationLog.OPT_ADD);
		return id;
	}

	@Override
	public void modifyFolder(Document document) throws BizfwServiceException {
		Document dbDocument = queryById(document.getIdBfDocument());
		String oldPath = dbDocument.getPath();
		String newPath = oldPath.replace(dbDocument.getName(), document.getName());
		dbDocument.setName(document.getName());
		dbDocument.setMemo(document.getMemo());
		dbDocument.update(document.getUpdateBy());
		update(dbDocument);
		modifyChildPath(dbDocument, oldPath, newPath);
		documentOptLogService.addLog(document, DocumentOperationLog.OPT_MOD);
	}

	@Override
	public void deleteFolder(String documentId, People people) throws BizfwServiceException {
		Document document = queryById(documentId);
		checkObjectNotNull(document, "文件夹[" + documentId + "]", "删除文件夹");
		checkCanDeleteFolder(document);
		roleDocumentRelDao.deleteByFieldAndValue(RoleDocumentRelation.COLUMN_DOCUMENT_ID, documentId);
		delete(documentId);
		document.setUpdateBy(people.getCode());
		documentOptLogService.addLog(document, DocumentOperationLog.OPT_DEL);
	}

	@Override
	public void setRoleOfFolder(Document folder, List<String> roleIdList) throws BizfwServiceException {
		roleDocumentRelDao.deleteByFieldAndValue(RoleDocumentRelation.COLUMN_DOCUMENT_ID, folder.getIdBfDocument());
		for (String roleId : roleIdList) {
			RoleDocumentRelation relation = new RoleDocumentRelation(folder.getUpdateBy(), roleId,
					folder.getIdBfDocument());
			roleDocumentRelDao.save(relation);
		}
	}

	@Override
	public List<Role> getRoleOfFolder(Document folder) throws BizfwServiceException {
		List<Role> roleList = new ArrayList<Role>();
		List<RoleDocumentRelation> relationList = roleDocumentRelDao
				.queryByFieldAndValue(RoleDocumentRelation.COLUMN_DOCUMENT_ID, folder.getIdBfDocument());
		for (RoleDocumentRelation relation : relationList) {
			Role role = roleService.queryById(relation.getIdBfRole());
			roleList.add(role);
		}
		return roleList;
	}

	/**
	 * 转换文档列表为文档树接口
	 * 
	 * @param list
	 *            文档列表
	 * @param rootFolder
	 *            根文档
	 * @return 文档树
	 * @throws BizfwServiceException
	 */
	private Document convertListToTree(List<Document> list, Document rootFolder) throws BizfwServiceException {
		List<Document> childList = new ArrayList<Document>();
		for (Document folder : list) {
			if (rootFolder.getIdBfDocument().equals(folder.getOwnerDocumentId())) {
				Document childFolder = convertListToTree(list, folder);
				childList.add(childFolder);
			}
		}
		rootFolder.setChildList(childList);
		return rootFolder;
	}

	/**
	 * 
	 * @param authFolderList
	 * @throws BizfwServiceException
	 */
	private void replenishAuthFolderList(List<Document> authFolderList) throws BizfwServiceException {
		List<Document> parentFolderList = new ArrayList<Document>();
		for (Document folder : authFolderList) {
			List<Document> tempParentFolderList = replenishParentFolder(folder);
			parentFolderList.addAll(tempParentFolderList);
		}
		for (Document folder : parentFolderList) {
			if (!authFolderList.contains(folder)) {
				authFolderList.add(folder);
			}
		}
	}

	private List<Document> replenishParentFolder(Document childFolder) throws BizfwServiceException {
		List<Document> parentFolderList = new ArrayList<Document>();
		while (StringUtils.isNotEmpty(childFolder.getOwnerDocumentId())) {
			Document parentFolder = queryById(childFolder.getOwnerDocumentId());
			parentFolderList.add(parentFolder);
			childFolder = parentFolder;
		}
		return parentFolderList;
	}

	private void modifyChildPath(Document document, String oldPrefix, String newPrefix) throws BizfwServiceException {
		String path = document.getPath();
		String newPath = path.replace(oldPrefix, newPrefix);
		if (!path.equals(newPath)) {
			document.setPath(newPath);
			update(document);
		}
		List<Document> list = folderDao.getChildFolderList(document);
		if (ListUtils.isNotEmpty(list)) {
			for (Document folder : list) {
				modifyChildPath(folder, oldPrefix, newPrefix);
			}
		}
	}

	/**
	 * 检查文件夹是否可删除
	 * 
	 * @param document
	 *            文档
	 * @throws BizfwServiceException
	 */
	private void checkCanDeleteFolder(Document document) throws BizfwServiceException {
		long childCount = folderDao.getChildCount(document);
		if (childCount > 0) {
			throw new BizfwServiceException(ErrorCode.Doc.Folder.DEL_FAIL_WITH_CHILD);
		}
	}

	@Override
	public BaseDao<Document> getBaseDao() {
		return folderDao;
	}

}
