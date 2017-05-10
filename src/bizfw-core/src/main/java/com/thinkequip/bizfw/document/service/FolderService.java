package com.thinkequip.bizfw.document.service;

import java.util.List;

import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.po.model.People;

/**
 * 文件夹服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月21日
 */
public interface FolderService extends BaseService<Document> {

	/**
	 * 获取所有所有文件夹
	 * 
	 * @return 文件夹树结构
	 * @throws BizfwServiceException
	 */
	public Document getAllFolderTree() throws BizfwServiceException;

	/**
	 * 获取人员有访问权限的的文件夹树
	 * 
	 * @param people
	 *            人员
	 * @return 文件夹树结构
	 * @throws BizfwServiceException
	 */
	public Document getAuthFolderTree(People people) throws BizfwServiceException;

	/**
	 * 获取角色有访问权限的文件夹树
	 * 
	 * @param role
	 *            角色
	 * @return 文件夹树结构
	 * @throws BizfwServiceException
	 */
	public List<Document> getFolderOfRole(Role role) throws BizfwServiceException;

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
	 * @param people
	 *            操作人员
	 * @throws BizfwServiceException
	 */
	public void deleteFolder(String documentId, People people) throws BizfwServiceException;

	/**
	 * 设置文件夹的可访问的对应角色权限
	 * 
	 * @param folder
	 *            文件夹
	 * @param roleIdList
	 *            角色id列表
	 * @throws BizfwServiceException
	 */
	public void setRoleOfFolder(Document folder, List<String> roleIdList) throws BizfwServiceException;

	/**
	 * 获取文件夹对应可访问角色列表
	 * 
	 * @param folder
	 *            文件夹
	 * @return 角色列表
	 * @throws BizfwServiceException
	 */
	public List<Role> getRoleOfFolder(Document folder) throws BizfwServiceException;
}
