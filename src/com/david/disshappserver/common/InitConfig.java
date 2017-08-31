/**
 * 
 */
package com.david.disshappserver.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * 初始化配置文件的工具类
 */
public class InitConfig {
	static Properties prop = new Properties();
	static {
		try {
			InputStream in = InitConfig.class.getResourceAsStream("/config/db.properties");
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		if (prop.containsKey(key)) {
			return prop.get(key).toString();
		}
		return "";
	}
}
