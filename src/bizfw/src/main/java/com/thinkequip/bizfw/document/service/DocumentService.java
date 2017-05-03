package com.thinkequip.bizfw.document.service;

import java.util.List;

import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.model.RoleDocumentRelation;
import com.thinkequip.bizfw.po.model.People;

/**
 * 文档服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Deprecated
public interface DocumentService extends BaseService<Document> {

	/**
	 * 获取所有所有文件夹
	 * 
	 * @return 文档树
	 * @throws BizfwServiceException
	 */
	public Document getAllFolderTree() throws BizfwServiceException;

	/**
	 * 获取人员关联的文件夹树
	 * 
	 * @param people
	 *            人员
	 * @return 文档树
	 * @throws BizfwServiceException
	 */
	public Document getAuthFolderTree(People people) throws BizfwServiceException;

	/**
	 * 获取角色关联的文件夹树
	 * 
	 * @param role
	 *            角色
	 * @return 文件夹树
	 * @throws BizfwServiceException
	 */
	public List<Document> getFolderOfRole(Role role) throws BizfwServiceException;

	/**
	 * 获取文件夹关联角色列表
	 * 
	 * @param folder
	 *            文档
	 * @return 角色列表
	 * @throws BizfwServiceException
	 */
	public List<Role> getRoleOfFolder(Document folder) throws BizfwServiceException;

	/**
	 * 新增文件夹
	 * 
	 * @param document
	 *            文档
	 * @return 主键
	 * @throws BizfwServiceException
	 */
	public String addFolder(Document document) throws BizfwServiceException;

	/**
	 * 修改文件夹
	 * 
	 * @param document
	 *            文档
	 * @throws BizfwServiceException
	 */
	public void modifyFolder(Document document) throws BizfwServiceException;

	/**
	 * 删除文件夹
	 * 
	 * @param documentId
	 *            文档id
	 * @throws BizfwServiceException
	 */
	public void deleteFolder(String documentId) throws BizfwServiceException;

	/**
	 * 设置角色关联的文件夹
	 * 
	 * @param role
	 *            角色
	 * @param folderIdList
	 *            文件夹id列表
	 * @throws BizfwServiceException
	 */
	public void setFolderOfRole(Role role, List<String> folderIdList) throws BizfwServiceException;

	/**
	 * 设置
	 * 
	 * @param folder
	 * @param roleIdList
	 * @throws BizfwServiceException
	 */
	public void setRoleOfFolder(Document folder, List<String> roleIdList) throws BizfwServiceException;

	/**
	 * 根据id获取角色与文档关联关系
	 * 
	 * @param relationId
	 *            关联关系id
	 * @return 角色文档关联关系
	 * @throws BizfwServiceException
	 */
	public RoleDocumentRelation getRelationById(String relationId) throws BizfwServiceException;

	/**
	 * 修改角色文档关联关系
	 * 
	 * @param relation
	 *            角色文档关联关系
	 * @throws BizfwServiceException
	 */
	public void modifyRelation(RoleDocumentRelation relation) throws BizfwServiceException;

	/**
	 * 新增文件
	 * 
	 * @param document
	 *            文档
	 * @return 主键
	 * @throws BizfwServiceException
	 */
	public String addFile(Document document) throws BizfwServiceException;

	/**
	 * 修改文件
	 * 
	 * @param document
	 *            文档
	 * @throws BizfwServiceException
	 */
	public void modifyFile(Document document) throws BizfwServiceException;

	/**
	 * 更新文件版本
	 * 
	 * @param document
	 *            文档
	 * @return 主键
	 * @throws BizfwServiceException
	 */
	public String updateFile(Document document) throws BizfwServiceException;

	/**
	 * 下载文件
	 * 
	 * @param document
	 *            文档
	 * @throws BizfwServiceException
	 */
	public void downloadFile(Document document) throws BizfwServiceException;

	/**
	 * 删除文件
	 * 
	 * @param document
	 *            文档
	 * @throws BizfwServiceException
	 */
	public void deleteFile(Document document) throws BizfwServiceException;

	/**
	 * 获取文档历史版本信息
	 * 
	 * @param document
	 *            文档
	 * @return 文档列表
	 * @throws BizfwServiceException
	 */
	public List<Document> getHistoryFileList(Document document) throws BizfwServiceException;

	/**
	 * 获取文件夹下内容，包含文件夹及文件
	 * 
	 * @param document
	 *            文档
	 * @return 文档列表
	 * @throws BizfwServiceException
	 */
	public List<Document> getContentOfFolder(Document document) throws BizfwServiceException;

}
