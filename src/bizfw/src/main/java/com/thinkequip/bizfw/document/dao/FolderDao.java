package com.thinkequip.bizfw.document.dao;

import java.util.List;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.document.model.Document;

/**
 * 文件夹dao接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月21日
 */
public interface FolderDao extends BaseDao<Document> {

	/**
	 * 获取子文档数量
	 * 
	 * @param document
	 *            文档
	 * @return 子文档数量
	 */
	public long getChildCount(Document document);

	/**
	 * 获取所有文件夹
	 * 
	 * @return 文件夹列表
	 */
	public List<Document> getAllFolder();

	/**
	 * 获取跟文件夹
	 * 
	 * @return 文档
	 */
	public Document getRootFolder();

	/**
	 * 获取子文件夹列表
	 * 
	 * @param document
	 *            文档
	 * @return 子文件夹列表
	 */
	public List<Document> getChildFolderList(Document document);

	/**
	 * 获取子文件夹与子文件
	 * 
	 * @param document
	 *            文档
	 * @return 文档列表
	 */
	public List<Document> getContentOfFolder(Document document);
}
