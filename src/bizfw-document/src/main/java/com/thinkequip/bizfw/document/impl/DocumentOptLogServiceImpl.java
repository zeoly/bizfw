package com.thinkequip.bizfw.document.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.base.impl.BaseServiceImpl;
import com.thinkequip.bizfw.document.dao.DocumentOptLogDao;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.model.DocumentOperationLog;
import com.thinkequip.bizfw.document.service.DocumentOptLogService;

@Service("documentOptLogService")
public class DocumentOptLogServiceImpl extends BaseServiceImpl<DocumentOperationLog> implements DocumentOptLogService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DocumentOptLogDao documentOptLogDao;

	@Override
	public void addLog(Document document, String operation) {
		try {
			DocumentOperationLog log = new DocumentOperationLog(document.getUpdateBy());
			log.setOperation(operation);
			log.setTargetId(document.getIdBfDocument());
			log.setTargetName(document.getName());
			log.setTargetType(document.getType());
			save(log);
		} catch (Exception e) {
			logger.error("写入文件操作日志失败", e);
		}
	}

	@Override
	public BaseDao<DocumentOperationLog> getBaseDao() {
		return documentOptLogDao;
	}

}
