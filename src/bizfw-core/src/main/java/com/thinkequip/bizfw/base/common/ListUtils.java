package com.thinkequip.bizfw.base.common;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListUtils {

	public static boolean isEmpty(List<?> list) {
		if (list != null && !list.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> void sort(List<T> list, final String fieldName, final boolean isAsc) {
		Collections.sort(list, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				try {
					Class clazz1 = ((T) o1).getClass();
					Class clazz2 = ((T) o2).getClass();
					String getterName = "get" + fieldName.substring(0, 1).toUpperCase()
							+ fieldName.replaceFirst("\\w", "");
					Method getter1 = clazz1.getMethod(getterName);
					Method getter2 = clazz2.getMethod(getterName);
					if (isAsc) {
						return getter1.invoke((T) o1).toString().compareTo(getter2.invoke((T) o2).toString());
					} else {
						return getter2.invoke((T) o2).toString().compareTo(getter1.invoke((T) o1).toString());
					}
				} catch (Exception e) {
					throw new RuntimeException("");
				}
			}

		});

	}

	public static <T> void sort(List<T> list, String fieldName) {
		sort(list, fieldName, true);
	}
}
