package com.thinkequip.bizfw.auth.impl;

import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.auth.dao.RoleMenuRelationDao;
import com.thinkequip.bizfw.auth.model.RoleMenuRelation;
import com.thinkequip.bizfw.base.impl.BaseDaoImpl;

/**
 * 角色菜单关联关系dao实现
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Repository("roleMenuRelationDao")
public class RoleMenuRelationDaoImpl extends BaseDaoImpl<RoleMenuRelation> implements RoleMenuRelationDao {

}
