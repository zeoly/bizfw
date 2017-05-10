package com.thinkequip.bizfw.document.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 文件夹模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月4日
 */
@Entity
@Table(name = "bf_folder")
public class Folder extends BaseModel {

	private static final long serialVersionUID = -1240258850763689533L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_folder")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfFolder;

	/** 文件夹名 */
	@Column(name = "name")
	private String name;

	/** 路径 */
	@Column(name = "path")
	private String path;

	/** 父文件夹id */
	@Column(name = "parent_id")
	private String parentId;

	@Transient
	private List<Folder> childFolderList;

	public Folder(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfFolder() {
		return idBfFolder;
	}

	public void setIdBfFolder(String idBfFolder) {
		this.idBfFolder = idBfFolder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Folder> getChildFolderList() {
		return childFolderList;
	}

	public void setChildFolderList(List<Folder> childFolderList) {
		this.childFolderList = childFolderList;
	}
}
