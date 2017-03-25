package com.thinkequip.bizfw.po.impl;

import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.base.impl.BaseDaoImpl;
import com.thinkequip.bizfw.po.dao.PeopleDao;
import com.thinkequip.bizfw.po.model.People;

/**
 * 人员信息dao实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Repository("peopleDao")
public class PeopleDaoImpl extends BaseDaoImpl<People> implements PeopleDao {

}
