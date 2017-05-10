package com.thinkequip.bizfw.base.common;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesUtils {

	public static String getBaseConfig(String key) {
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("");
			Properties config = new Properties();
			config.load(inputStream);
			return config.getProperty(key);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return null;
		}
	}

	public static String getErrorMsg(String errrorCode) {
		ResourceBundle rb = ResourceBundle.getBundle("error", Locale.CHINA);
		String str = rb.getString(errrorCode);
		return str;
	}

	public static String getSysConfig(String cfgKey) {
		ResourceBundle rb = ResourceBundle.getBundle("bizfw", Locale.CHINA);
		String str = rb.getString(cfgKey);
		return str;
	}
}
