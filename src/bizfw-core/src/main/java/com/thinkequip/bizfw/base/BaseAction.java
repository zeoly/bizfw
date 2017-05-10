package com.thinkequip.bizfw.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.thinkequip.bizfw.po.model.People;

/**
 * 框架基础action
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
public class BaseAction {

	public static final String PEOPLE_KEY = "peopleInfo";

	protected static final String SUCCESS = "success";

	public People getLoginPeople(HttpServletRequest request) {
		return (People) request.getSession().getAttribute(PEOPLE_KEY);
	}

	public void setLoginPeople(HttpServletRequest request, People peopleInfo) {
		request.getSession().setAttribute(PEOPLE_KEY, peopleInfo);
	}

	public void removeLoginInfo(HttpServletRequest request) {
		request.getSession().removeAttribute(PEOPLE_KEY);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

}
