package com.thinkequip.bizfw.base.model;

import java.io.Serializable;

/**
 * 框架基础响应类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 7147705981259466203L;

	public boolean success;

	public String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object data;
}
