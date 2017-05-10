package com.thinkequip.bizfw.po.service;

import java.util.List;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.po.model.Department;

/**
 * 机构服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
public interface DepartmentService extends BaseService<Department> {

	/**
	 * 增加机构
	 * 
	 * @param department
	 *            机构
	 * @throws BizfwServiceException
	 */
	public void addDepartment(Department department) throws BizfwServiceException;

	/**
	 * 修改机构，更新机构名、机构代码
	 * 
	 * @param department
	 *            机构
	 * @throws BizfwServiceException
	 */
	public void modifyDepartment(Department department) throws BizfwServiceException;

	/**
	 * 删除机构（根据id）
	 * 
	 * @param department
	 *            机构
	 * @throws BizfwServiceException
	 */
	public void deleteDepartment(Department department) throws BizfwServiceException;

	/**
	 * 删除所有上级机构关联关系
	 * 
	 * @param department
	 *            机构
	 * @throws BizfwServiceException
	 */
	public void deleteUpperDepartmentRelation(Department department) throws BizfwServiceException;

	/**
	 * 根据机构代码获取机构信息
	 * 
	 * @param code
	 *            机构代码
	 * @return 机构
	 * @throws BizfwServiceException
	 */
	public Department queryByCode(String code) throws BizfwServiceException;

	/**
	 * 判断是否机构下存在子机构
	 * 
	 * @param department
	 *            机构
	 * @return boolean
	 * @throws BizfwServiceException
	 */
	public boolean hasChildDepartment(Department department) throws BizfwServiceException;

	/**
	 * 判断机构下是否存在人员
	 * 
	 * @param department
	 *            机构
	 * @return boolean
	 * @throws BizfwServiceException
	 */
	public boolean hasPeople(Department department) throws BizfwServiceException;

	/**
	 * 获取父机构
	 * 
	 * @param department
	 *            机构
	 * @return 父机构
	 * @throws BizfwServiceException
	 */
	public Department getParentDepartment(Department department) throws BizfwServiceException;

	/**
	 * 获取子机构列表
	 * 
	 * @param deparmentId
	 *            机构id
	 * @return 子机构列表
	 * @throws BizfwServiceException
	 */
	public List<Department> getChildDepartmentList(String deparmentId) throws BizfwServiceException;

	/**
	 * 获取所有上级机构信息（包含跨级）
	 * 
	 * @param deparmentId
	 *            机构id
	 * @return 父机构列表
	 * @throws BizfwServiceException
	 */
	public List<Department> getAllParentDeptmentList(String deparmentId) throws BizfwServiceException;

	/**
	 * 获取所有子机构列表（包含跨级）
	 * 
	 * @param deparmentId
	 *            机构id
	 * @return 子机构列表
	 * @throws BizfwServiceException
	 */
	public List<Department> getAllChildDeptmentList(String deparmentId) throws BizfwServiceException;

	/**
	 * 获取当前机构下的机构树
	 * 
	 * @param deparmentId
	 *            机构id
	 * @return 机构树
	 * @throws BizfwServiceException
	 */
	public Department getDepartmentTreeByDepartmentId(String deparmentId) throws BizfwServiceException;

}
