package com.thinkequip.bizfw.base.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.common.StringUtils;
import com.thinkequip.bizfw.po.model.People;

public class LoginFilter implements Filter {

	String excludedUrl;

	String[] excludedUrls;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		if (!isExcluded(url)) {
			HttpSession session = request.getSession();
			People peopleInfo = (People) session.getAttribute(BaseAction.PEOPLE_KEY);
			if (peopleInfo == null) {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		excludedUrl = config.getInitParameter("excludedUrl");
		if (StringUtils.isNotEmpty(excludedUrl)) {
			excludedUrls = excludedUrl.split(",");
		}
		return;
	}

	private boolean isExcluded(String url) {
		for (String str : excludedUrls) {
			Pattern pattern = Pattern.compile(str);
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				return true;
			}
		}
		return false;
	}
}
