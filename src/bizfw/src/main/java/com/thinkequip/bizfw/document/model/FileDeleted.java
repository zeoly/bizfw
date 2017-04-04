package com.thinkequip.bizfw.document.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thinkequip.bizfw.base.model.BaseModel;

@Entity
@Table(name = "bf_file_deleted")
public class FileDeleted extends BaseModel {

	private static final long serialVersionUID = -2647860587859869302L;

	@Id
	@Column(name = "id_bf_file_deleted")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String idBfFileDeleted;

	@Column(name = "url")
	private String url;

	public FileDeleted(String peopleCode) {
		super(peopleCode);
	}

	public FileDeleted(String peopleCode, String url) {
		super(peopleCode);
		this.url = url;
	}

	public String getIdBfFileDeleted() {
		return idBfFileDeleted;
	}

	public void setIdBfFileDeleted(String idBfFileDeleted) {
		this.idBfFileDeleted = idBfFileDeleted;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
