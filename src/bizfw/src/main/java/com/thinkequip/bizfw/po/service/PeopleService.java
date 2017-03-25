package com.thinkequip.bizfw.po.service;

import java.util.List;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.po.model.Department;
import com.thinkequip.bizfw.po.model.People;

/**
 * 人员服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
public interface PeopleService extends BaseService<People> {

	/**
	 * 新增人员
	 * 
	 * @param people
	 *            人员模型
	 * @return 主键
	 * @throws BizfwServiceException
	 */
	public String addPeople(People people) throws BizfwServiceException;

	/**
	 * 修改人员信息
	 * 
	 * @param people
	 *            人员模型
	 * @throws BizfwServiceException
	 */
	public void modifyPeople(People people) throws BizfwServiceException;

	/**
	 * 根据id删除人员记录
	 * 
	 * @param people
	 *            人员信息
	 * @throws BizfwServiceException
	 */
	public void deletePeople(People people) throws BizfwServiceException;

	/**
	 * 根据人员代码获取人员信息
	 * 
	 * @param code
	 *            人员代码
	 * @return 人员信息
	 * @throws BizfwServiceException
	 */
	public People getByCode(String code) throws BizfwServiceException;

	/**
	 * 获取机构下人员数量
	 * 
	 * @param department
	 *            机构
	 * @return 人员数量
	 * @throws BizfwServiceException
	 */
	public long getPeopleCountByDepartment(Department department) throws BizfwServiceException;

	/**
	 * 获取机构下人员列表
	 * 
	 * @param departmentId
	 *            机构id
	 * @return 人员信息列表
	 * @throws BizfwServiceException
	 */
	public List<People> getPeopleListByDepartment(String departmentId) throws BizfwServiceException;

	/**
	 * 解锁状态为“锁定”的用户
	 * 
	 * @param people
	 *            人员信息
	 * @throws BizfwServiceException
	 */
	public void unlock(People people) throws BizfwServiceException;

	/**
	 * 修改用户密码
	 * 
	 * @param people
	 *            人员信息
	 * @param oldPwd
	 *            旧密码
	 * @param newPwd
	 *            新密码
	 * @throws BizfwServiceException
	 */
	public void modifyPassword(People people, String oldPwd, String newPwd) throws BizfwServiceException;
}
