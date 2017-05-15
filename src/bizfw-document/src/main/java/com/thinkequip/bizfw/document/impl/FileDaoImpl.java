package com.thinkequip.bizfw.document.impl;

import org.springframework.stereotype.Repository;

import com.thinkequip.bizfw.base.impl.BaseDaoImpl;
import com.thinkequip.bizfw.document.dao.FileDao;
import com.thinkequip.bizfw.document.model.Document;

/**
 * 文件dao实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月21日
 */
@Repository("fileDao")
public class FileDaoImpl extends BaseDaoImpl<Document> implements FileDao {

}
