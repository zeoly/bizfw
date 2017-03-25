package com.thinkequip.bizfw.base.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.PropertiesUtils;
import com.thinkequip.bizfw.base.common.StringUtils;
import com.thinkequip.bizfw.po.model.People;
import com.thinkequip.bizfw.po.service.PeopleService;

/**
 * 登录action
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
@Controller
@RequestMapping("/loginAction")
public class LoginAction extends BaseAction {

	private static final int DEFAULT_lOCK_COUNT = Integer.parseInt(PropertiesUtils.getSysConfig("pwd.error.count"));

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	PeopleService peopleService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录信息
	 * @throws BizfwServiceException
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public String login(HttpServletRequest request, String username, String password) throws BizfwServiceException {
		People peopleInfo = peopleService.getByCode(username);
		String result = "";
		if (peopleInfo == null || People.STATUS_INVALID.equals(peopleInfo.getStatus())) {
			result = "账户不存在";
		} else if (People.STATUS_LOCKED.equals(peopleInfo.getStatus())) {
			result = "账户已锁定，请联系管理员解锁";
		} else if (People.STATUS_UNCHECK.equals(peopleInfo.getStatus())) {
			result = "账户正在审核中，请耐心等待";
		} else if (!StringUtils.encryptMD5(password).equals(peopleInfo.getPassword())) {
			peopleInfo.setErrorCount(peopleInfo.getErrorCount() + 1);
			if (peopleInfo.getErrorCount() >= DEFAULT_lOCK_COUNT) {
				peopleInfo.setStatus(People.STATUS_LOCKED);
			}
			logger.info("{}尝试登录失败，密码错误次数{}", peopleInfo.getCode(), peopleInfo.getErrorCount());
			peopleService.update(peopleInfo);
			int remainTrials = DEFAULT_lOCK_COUNT - peopleInfo.getErrorCount() + 1;
			result = "密码错误, 你还有" + remainTrials + "次机会尝试";
		} else if (StringUtils.encryptMD5(password).equals(peopleInfo.getPassword())) {
			logger.info("{}登录系统", peopleInfo.getCode());
			setLoginPeople(request, peopleInfo);
			peopleInfo.setErrorCount(0);
			peopleService.update(peopleInfo);
			result = "Y";
		} else {
			result = "未知错误";
		}
		return result;
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 * @return 登出成功
	 * @throws IOException
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		People people = getLoginPeople(request);
		logger.info("{}登出系统", people.getCode());
		removeLoginInfo(request);
		request.getSession().invalidate();
		return SUCCESS;
	}

}
