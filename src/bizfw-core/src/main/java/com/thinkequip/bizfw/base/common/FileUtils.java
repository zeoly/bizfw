package com.thinkequip.bizfw.base.common;

import java.io.File;
import java.util.UUID;

public class FileUtils {

	public static String getExtension(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		return extension;
	}

	public static String getStorageUrl(String fileName) {
		String storageFileName = UUID.randomUUID().toString() + "_" + fileName;
		int hashcode = fileName.hashCode();
		int dir1 = hashcode & 0xf;
		// int dir2 = (hashcode & 0xf0) >> 4;
		String url = "/" + DateUtils.getDateStr() + "/" + dir1;
		String localFolder = getLocalStorage() + url;
		File file = new File(localFolder);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fullUrl = url + "/" + storageFileName;
		return fullUrl;
	}

	public static String getLocalStorage() {
		return PropertiesUtils.getSysConfig("local.storage");
	}
}
