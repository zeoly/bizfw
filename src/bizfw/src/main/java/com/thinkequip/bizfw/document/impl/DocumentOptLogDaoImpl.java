package com.thinkequip.bizfw.document.impl;

import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.base.impl.BaseDaoImpl;
import com.thinkequip.bizfw.document.dao.DocumentOptLogDao;
import com.thinkequip.bizfw.document.model.DocumentOperationLog;

@Repository("documentOptLogDao")
public class DocumentOptLogDaoImpl extends BaseDaoImpl<DocumentOperationLog> implements DocumentOptLogDao {

}
