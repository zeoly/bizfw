package com.thinkequip.bizfw.document.impl;

import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.base.impl.BaseDaoImpl;
import com.thinkequip.bizfw.document.dao.FileDeletedDao;
import com.thinkequip.bizfw.document.model.FileDeleted;

@Repository("fileDeletedDao")
public class FileDeletedDaoImpl extends BaseDaoImpl<FileDeleted> implements FileDeletedDao {

}
