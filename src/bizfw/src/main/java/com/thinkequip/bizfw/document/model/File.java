package com.thinkequip.bizfw.document.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 文件模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年5月4日
 */
@Entity
@Table(name = "bf_file")
public class File extends BaseModel {

	private static final long serialVersionUID = -4810580352680756463L;

	/** 主键 */
	@Id
	@Column(name = "id_bf_file")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfFile;

	/** 文件名 */
	@Column(name = "name")
	private String name;

	/** 扩展名 */
	@Column(name = "extension")
	private String extension;

	/** 文件路径，包含所有上级文件夹 */
	@Column(name = "path")
	private String path;

	/** 文件物理存储url，下载用 */
	@Column(name = "url")
	private String url;

	/** 文件大小，单位byte */
	@Column(name = "size")
	private Long size;

	/** 备注 */
	@Column(name = "memo")
	private String memo;

	/** 所属文件夹id */
	@Column(name = "folder_id")
	private String folderId;

	/** 版本 */
	@Column(name = "revision")
	private Integer revision;

	/** 下载次数 */
	@Column(name = "download_count")
	private Integer downloadCount;

	/** 文档对应md5 */
	@Column(name = "md5")
	private String md5;

	public File(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfFile() {
		return idBfFile;
	}

	public void setIdBfFile(String idBfFile) {
		this.idBfFile = idBfFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
