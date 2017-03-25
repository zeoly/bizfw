package com.thinkequip.bizfw.document.dao;

import com.thinkequip.bizfw.base.BaseDao;
import com.thinkequip.bizfw.document.model.RoleDocumentRelation;

public interface RoleDocumentRelDao extends BaseDao<RoleDocumentRelation> {

	public RoleDocumentRelation getRelation(String roleId, String documentId);
}
