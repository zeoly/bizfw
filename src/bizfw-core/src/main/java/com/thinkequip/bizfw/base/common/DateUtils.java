package com.thinkequip.bizfw.base.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
public class DateUtils {

	/**
	 * 获取当前日期字符串
	 * 
	 * @return yyyy-MM-dd格式字符串
	 */
	public static String getDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
}
