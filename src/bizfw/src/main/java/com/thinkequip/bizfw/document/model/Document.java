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
 * 文档模型，包含文件/文件夹
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Entity
@Table(name = "bf_document")
public class Document extends BaseModel {

	private static final long serialVersionUID = -7390005208539497792L;

	public static final String TYPE_DIRECTORY = "0";

	public static final String TYPE_FILE = "1";

	public static final String ROOT_NAME = "/";

	public static final int REVISION_FIRST = 1;

	public static final String COLUMN_NAME = "name";

	public static final String COLUMN_TYPE = "type";

	public static final String COLUMN_OWNER_DOCUMENT_ID = "ownerDocumentId";

	public static final String COLUMN_REVISION = "revision";

	/** 主键 */
	@Id
	@Column(name = "id_bf_document")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	private String idBfDocument;

	/** 文档名 */
	@Column(name = "name")
	private String name;

	/** 扩展名 */
	@Column(name = "extension")
	private String extension;

	/** 文档类型 */
	@Column(name = "type")
	private String type;

	/** 路径 */
	@Column(name = "path")
	private String path;

	/** 文档存储url */
	@Column(name = "url")
	private String url;

	/** 文件大小 */
	@Column(name = "size")
	private Long size;

	/** 备注 */
	@Column(name = "memo")
	private String memo;

	/** 所属文档id */
	@Column(name = "owner_document_id")
	private String ownerDocumentId;

	/** 版本 */
	@Column(name = "revision")
	private Integer revision;

	/** 下载次数 */
	@Column(name = "download_count")
	private Integer downloadCount;

	/** 文档对应md5 */
	@Column(name = "md5")
	private String md5;

	@Transient
	private List<Document> childList;

	public Document() {
		super();
	}

	public Document(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfDocument() {
		return idBfDocument;
	}

	public void setIdBfDocument(String idBfDocument) {
		this.idBfDocument = idBfDocument;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getOwnerDocumentId() {
		return ownerDocumentId;
	}

	public void setOwnerDocumentId(String ownerDocumentId) {
		this.ownerDocumentId = ownerDocumentId;
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

	public List<Document> getChildList() {
		return childList;
	}

	public void setChildList(List<Document> childList) {
		this.childList = childList;
	}

}
