package com.thinkequip.bizfw.po.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.base.common.ListUtils;
import com.thinkequip.bizfw.base.impl.BaseDaoImpl;
import com.thinkequip.bizfw.po.dao.DepartmentRelationDao;
import com.thinkequip.bizfw.po.model.Department;
import com.thinkequip.bizfw.po.model.DepartmentRelation;

/**
 * 机构关联关系dao实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Repository("departmentRelationDao")
public class DepartmentRelationDaoImpl extends BaseDaoImpl<DepartmentRelation> implements DepartmentRelationDao {

	@Override
	public DepartmentRelation getParentDepartmentRel(Department department) {
		String hql = "from " + getTableName() + " as t where t." + DepartmentRelation.COLUMN_CHILD_DEPARTMENT_ID
				+ " = :child and t." + DepartmentRelation.COLUMN_PARENT_LEVEL + " = :level";
		Query<DepartmentRelation> query = createQuery(hql);
		query.setParameter("child", department.getIdBfDepartment());
		query.setParameter("level", department.getLevel() - 1);
		List<DepartmentRelation> list = query.list();
		if (ListUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<DepartmentRelation> getChildDepartmentRelList(Department department) {
		String hql = "from " + getTableName() + " as t where t." + DepartmentRelation.COLUMN_PARENT_DEPARTMENT_ID
				+ " = :parentId and t." + DepartmentRelation.COLUMN_PARENT_LEVEL + " = :level";
		Query<DepartmentRelation> query = createQuery(hql);
		query.setParameter("parentId", department.getIdBfDepartment());
		query.setParameter("level", department.getLevel());
		List<DepartmentRelation> list = query.list();
		return list;
	}
}
