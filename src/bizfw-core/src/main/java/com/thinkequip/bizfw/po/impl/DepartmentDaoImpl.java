package com.thinkequip.bizfw.po.impl;

import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.base.impl.BaseDaoImpl;
import com.thinkequip.bizfw.po.dao.DepartmentDao;
import com.thinkequip.bizfw.po.model.Department;

/**
 * 机构信息dao实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

}
