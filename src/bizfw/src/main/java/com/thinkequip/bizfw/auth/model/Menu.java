package com.thinkequip.bizfw.auth.model;

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
 * 菜单模型
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Entity
@Table(name = "bf_menu")
public class Menu extends BaseModel {

	private static final long serialVersionUID = 8163772024398615538L;

	public static final String ROOT_NAME = "root";

	public static final String COLUMN_NAME = "name";

	public static final String COLUMN_PARENT_MENU_ID = "parentMenuId";

	public static final String COLUMN_ORDERS = "orders";

	/** 主键 */
	@Id
	@Column(name = "id_bf_menu")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	private String idBfMenu;

	/** 菜单名称 */
	@Column(name = "name")
	private String name;

	/** 菜单跳转url */
	@Column(name = "url")
	private String url;

	/** 菜单顺序 */
	@Column(name = "orders")
	private String orders;

	/** 父菜单id */
	@Column(name = "parent_menu_id")
	private String parentMenuId;

	/** 子菜单列表 */
	@Transient
	private List<Menu> childList;

	public Menu(String peopleCode, String name, String url, String orders, String parentMenuId) {
		super(peopleCode);
		this.name = name;
		this.url = url;
		this.orders = orders;
		this.parentMenuId = parentMenuId;
	}

	public Menu() {
		super();
	}

	public String getIdBfMenu() {
		return idBfMenu;
	}

	public void setIdBfMenu(String idBfMenu) {
		this.idBfMenu = idBfMenu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}

}
