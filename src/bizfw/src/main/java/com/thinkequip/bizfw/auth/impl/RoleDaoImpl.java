package com.thinkequip.bizfw.auth.impl;

import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.auth.dao.RoleDao;
import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.base.impl.BaseDaoImpl;

/**
 * 角色dao实现
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

}
