package com.dji.itester.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 读取.properties格式的配置文件
 * 
 * @author Charlie.chen
 *
 */
public class PropertiesUtil {
	
	
	public static String getValue(String key,String configName) throws IOException{
		
		// 加载键值对数据
		Properties prop = new Properties();
		
		String configPath=PropertiesUtil.class.getClassLoader().getResource(configName).getPath();
		
		FileReader fr = new FileReader(configPath);
		prop.load(fr);
		fr.close();	
		// 获取数据
		String value = prop.getProperty(key);
		
		return value;
	}
}
