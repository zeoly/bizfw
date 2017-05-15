package com.thinkequip.bizfw.document.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.FileUtils;
import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.document.dao.FileDao;
import com.thinkequip.bizfw.document.dao.FileDeletedDao;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.model.DocumentOperationLog;
import com.thinkequip.bizfw.document.model.FileDeleted;
import com.thinkequip.bizfw.document.service.DocumentOptLogService;
import com.thinkequip.bizfw.document.service.FileService;

/**
 * 文件服务实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月21日
 */
@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl<Document> implements FileService {

	@Autowired
	private FileDao fileDao;

	@Autowired
	private FileDeletedDao fileDeletedDao;

	@Autowired
	private DocumentOptLogService documentOptLogService;

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

	@Override
	public BaseDao<Document> getBaseDao() {
		return fileDao;
	}

}
