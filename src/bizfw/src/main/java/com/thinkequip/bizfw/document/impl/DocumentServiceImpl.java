package com.thinkequip.bizfw.document.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.auth.service.RoleService;
import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.FileUtils;
import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.base.common.StringUtils;
import com.thinkequip.bizfw.base.consts.ErrorCode;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.document.dao.DocumentDao;
import com.thinkequip.bizfw.document.dao.FileDeletedDao;
import com.thinkequip.bizfw.document.dao.RoleDocumentRelDao;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.model.DocumentOperationLog;
import com.thinkequip.bizfw.document.model.FileDeleted;
import com.thinkequip.bizfw.document.model.RoleDocumentRelation;
import com.thinkequip.bizfw.document.service.DocumentOptLogService;
import com.thinkequip.bizfw.document.service.DocumentService;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.bizfw.po.service.PeopleService;

/**
 * 文档服务
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Service("documentService")
public class DocumentServiceImpl extends BaseServiceImpl<Document> implements DocumentService {

	@Autowired
	private DocumentDao documentDao;

	@Autowired
	private RoleDocumentRelDao roleDocumentRelDao;

	@Autowired
	private FileDeletedDao fileDeletedDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private DocumentOptLogService documentOptLogService;

	@Override
	public Document getAllFolderTree() throws BizfwServiceException {
		List<Document> folderList = documentDao.getAllFolder();
		ListUtils.sort(folderList, Document.COLUMN_NAME);
		Document rootFolder = documentDao.getRootFolder();
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
		Document rootFolder = documentDao.getRootFolder();
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

	@Transactional
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
	public void deleteFolder(String documentId) throws BizfwServiceException {
		Document document = queryById(documentId);
		checkObjectNotNull(document, "文件夹[" + documentId + "]", "删除文件夹");
		checkCanDeleteFolder(document);
		roleDocumentRelDao.deleteByFieldAndValue(RoleDocumentRelation.COLUMN_DOCUMENT_ID, documentId);
		delete(documentId);
		documentOptLogService.addLog(document, DocumentOperationLog.OPT_DEL);
	}

	@Transactional
	@Override
	public void setFolderOfRole(Role role, List<String> folderIdList) throws BizfwServiceException {
		roleDocumentRelDao.deleteByFieldAndValue(RoleDocumentRelation.COLUMN_ROLE_ID, role.getIdBfRole());
		for (String folderId : folderIdList) {
			RoleDocumentRelation relation = new RoleDocumentRelation(role.getUpdateBy(), role.getIdBfRole(), folderId);
			roleDocumentRelDao.save(relation);
		}
	}

	@Transactional
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
	public RoleDocumentRelation getRelationById(String relationId) throws BizfwServiceException {
		return roleDocumentRelDao.queryById(relationId);
	}

	@Override
	public void modifyRelation(RoleDocumentRelation relation) throws BizfwServiceException {
		roleDocumentRelDao.update(relation);
	}

	@Override
	public String addFile(Document document) throws BizfwServiceException {
		Document parentDocument = queryById(document.getOwnerDocumentId());
		checkObjectNotNull(parentDocument, "文件夹[" + document.getOwnerDocumentId() + "]", "新增文件");
		String ext = FileUtils.getExtension(document.getName());
		document.setType(Document.TYPE_FILE);
		document.setPath(parentDocument.getPath());
		document.setRevision(Document.REVISION_FIRST);
		document.setDownloadCount(0);
		document.setExtension(ext);
		String id = save(document);
		documentOptLogService.addLog(document, DocumentOperationLog.OPT_ADD);
		return id;
	}

	@Override
	public void modifyFile(Document document) throws BizfwServiceException {
		Document dbDocument = queryById(document.getIdBfDocument());
		dbDocument.setName(document.getName());
		dbDocument.setExtension(FileUtils.getExtension(document.getName()));
		dbDocument.update(document.getUpdateBy());
		update(dbDocument);
		documentOptLogService.addLog(document, DocumentOperationLog.OPT_MOD);
	}

	@Override
	public String updateFile(Document newFile) throws BizfwServiceException {
		String lastFileId = newFile.getOwnerDocumentId();
		Document lastFile = queryById(lastFileId);

		String ext = FileUtils.getExtension(newFile.getName());
		newFile.setExtension(ext);
		newFile.setType(Document.TYPE_FILE);
		newFile.setPath(lastFile.getPath());
		newFile.setOwnerDocumentId(lastFile.getOwnerDocumentId());
		newFile.setRevision(lastFile.getRevision() + 1);
		newFile.setDownloadCount(0);
		String newFileId = save(newFile);

		lastFile.setOwnerDocumentId(newFileId);
		update(lastFile);
		updateFileChain(lastFileId, newFileId);
		documentOptLogService.addLog(newFile, DocumentOperationLog.OPT_UPD);
		return newFileId;
	}

	@Override
	public void downloadFile(Document document) throws BizfwServiceException {
		int downloadCount = document.getDownloadCount();
		document.setDownloadCount(downloadCount + 1);
		update(document);

		documentOptLogService.addLog(document, DocumentOperationLog.OPT_DOWNLOAD);
	}

	@Override
	public void deleteFile(Document document) throws BizfwServiceException {
		fileDeletedDao.save(new FileDeleted(document.getUpdateBy(), document.getUrl()));
		delete(document.getIdBfDocument());
		documentOptLogService.addLog(document, DocumentOperationLog.OPT_DEL);
	}

	@Override
	public List<Document> getHistoryFileList(Document document) throws BizfwServiceException {
		List<Document> list = queryByFieldAndValue(Document.COLUMN_OWNER_DOCUMENT_ID, document.getIdBfDocument());
		ListUtils.sort(list, Document.COLUMN_REVISION);
		return list;
	}

	@Override
	public List<Document> getContentOfFolder(Document document) throws BizfwServiceException {
		checkAccessAuth(document);
		List<Document> list = documentDao.getContentOfFolder(document);
		return list;
	}

	/**
	 * 检查文件夹是否可删除
	 * 
	 * @param document
	 *            文档
	 * @throws BizfwServiceException
	 */
	private void checkCanDeleteFolder(Document document) throws BizfwServiceException {
		long childCount = documentDao.getChildCount(document);
		if (childCount > 0) {
			throw new BizfwServiceException(ErrorCode.Doc.Folder.DEL_FAIL_WITH_CHILD);
		}
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
	 * 跟新文件版本后，更新文档链，即历史版本的文件关联到新文件上
	 * 
	 * @param lastFileId
	 *            上一个版本文件id
	 * @param newFileId
	 *            新文件id
	 * @throws BizfwServiceException
	 */
	private void updateFileChain(String lastFileId, String newFileId) throws BizfwServiceException {
		List<Document> list = queryByFieldAndValue(Document.COLUMN_OWNER_DOCUMENT_ID, lastFileId);
		for (Document file : list) {
			file.setOwnerDocumentId(newFileId);
			update(file);
		}
	}

	/**
	 * 
	 * @param document
	 * @param oldPrefix
	 * @param newPrefix
	 * @throws BizfwServiceException
	 */
	private void modifyChildPath(Document document, String oldPrefix, String newPrefix) throws BizfwServiceException {
		String path = document.getPath();
		String newPath = path.replace(oldPrefix, newPrefix);
		if (!path.equals(newPath)) {
			document.setPath(newPath);
			update(document);
		}
		List<Document> list = documentDao.getChildFolderList(document);
		if (ListUtils.isNotEmpty(list)) {
			for (Document folder : list) {
				modifyChildPath(folder, oldPrefix, newPrefix);
			}
		}
	}

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

	/**
	 * 检查是否有文件权限
	 * @param folder
	 * @throws BizfwServiceException
	 */
	private void checkAccessAuth(Document folder) throws BizfwServiceException {
		People people = peopleService.getByCode(folder.getUpdateBy());
		List<Role> roleList = roleService.getRoleListByPeople(people.getIdBfPeople());
		boolean isfound = false;
		for (Role role : roleList) {
			RoleDocumentRelation relation = roleDocumentRelDao.getRelation(role.getIdBfRole(),
					folder.getIdBfDocument());
			if (relation != null) {
				isfound = true;
			}
		}
		if (!isfound) {
			throw new BizfwServiceException(ErrorCode.Doc.File.ACCESS_FAIL_NO_AUTH);
		}
	}

	@Override
	public BaseDao<Document> getBaseDao() {
		return documentDao;
	}

}
