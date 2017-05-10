package com.thinkequip.bizfw.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkequip.bizfw.base.common.PropertiesUtils;
import com.thinkequip.bizfw.base.consts.ErrorCode;

public class ExceptionInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex)
			throws Exception {
		if (ex != null) {
			if (ex instanceof BizfwServiceException) {
				BizfwServiceException e = (BizfwServiceException) ex;
				response.setContentType("application/json;charset=UTF-8");
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("errorMsg", e.getErrorMsg());
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().write(mapper.writeValueAsString(msg));
			} else {
				response.setContentType("application/json;charset=UTF-8");
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("errorMsg", PropertiesUtils.getErrorMsg(ErrorCode.DEFAULT_ERROR));
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().write(mapper.writeValueAsString(msg));
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
