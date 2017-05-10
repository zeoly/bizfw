package com.thinkequip.bizfw.base.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
public class StringUtils {

	static Logger logger = LoggerFactory.getLogger(StringUtils.class);

	/**
	 * 判断字符串是否为null，空字符串
	 * 
	 * @param str
	 *            目标字符串
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		return true;
	}

	/**
	 * 对字符串进行md5加密
	 * 
	 * @param password
	 *            目标字符串
	 * @return 经md5加密后的字符串
	 */
	public static String encryptMD5(String password) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			byte[] passwordInput = password.getBytes();
			mdInst.update(passwordInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			logger.error("字符串加密异常", e);
		}
		return null;
	}
}
