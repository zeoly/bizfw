package com.thinkequip.bizfw.document.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

/**
 * 文档操作日志模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Entity
@Table(name = "bf_document_opt_log")
public class DocumentOperationLog extends BaseModel {

	private static final long serialVersionUID = -4481884134578804202L;

	public static final String OPT_ADD = "1";

	public static final String OPT_MOD = "2";

	public static final String OPT_DEL = "3";

	public static final String OPT_UPD = "4";

	public static final String OPT_DOWNLOAD = "5";

	/** 主键 */
	@Id
	@Column(name = "id_bf_document_opt_log")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	private String idBfDocumentOptLog;

	/** 操作类型 */
	@Column(name = "operation")
	private String operation;

	/** 操作文档类型 */
	@Column(name = "target_type")
	private String targetType;

	/** 文档主键 */
	@Column(name = "target_id")
	private String targetId;

	/** 文档名称 */
	@Column(name = "target_name")
	private String targetName;

	public DocumentOperationLog(String peopleCode) {
		super(peopleCode);
	}

	public String getIdBfDocumentOptLog() {
		return idBfDocumentOptLog;
	}

	public void setIdBfDocumentOptLog(String idBfDocumentOptLog) {
		this.idBfDocumentOptLog = idBfDocumentOptLog;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
}
