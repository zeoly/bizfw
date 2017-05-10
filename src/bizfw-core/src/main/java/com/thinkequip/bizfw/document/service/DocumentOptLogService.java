package com.thinkequip.bizfw.document.service;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.model.DocumentOperationLog;

/**
 * 文档操作日志服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
public interface DocumentOptLogService extends BaseService<DocumentOperationLog> {

	/**
	 * 新增文档日志
	 * 
	 * @param document
	 *            操作文档
	 * @param operation
	 *            操作类型
	 */
	public void addLog(Document document, String operation);
}
