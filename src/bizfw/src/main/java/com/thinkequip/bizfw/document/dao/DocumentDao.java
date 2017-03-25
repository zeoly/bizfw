package com.thinkequip.bizfw.document.dao;

import java.util.List;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.document.model.Document;

/**
 * 文档dao接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
public interface DocumentDao extends BaseDao<Document> {

	/**
	 * 
	 * @param document
	 * @return
	 */
	public long getChildCount(Document document);

	public List<Document> getAllFolder();

	public Document getRootFolder();

	public List<Document> getChildFolderList(Document document);

	public List<Document> getContentOfFolder(Document document);
}
