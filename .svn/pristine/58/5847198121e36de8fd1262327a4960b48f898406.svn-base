package com.ccx.models.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 属性文件操作工具类
 * @author xzd
 * @date 2017/6/29
 * 此类封装了读取系统配置文件的功能
 * 用法 : PropertiesUtil.getProperty(String keyName)
 */
public class PropertiesUtil {
	
	private static PropertiesUtil manager = null;
	private static Object managerLock = new Object();
	private Object propertiesLock = new Object();
	private static String TEMPLATE_CONFIG_FILE = "prop/resource-template.properties";
	private Properties properties = null;
	private static Map<String,ResourceBundle> pro_map=new ConcurrentHashMap<>();

	public static PropertiesUtil getInstance() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertiesUtil();
				}
			}
		}
		return manager;
	}

	private PropertiesUtil() {
	}

	public static String getProperty(String name) {
		return getInstance()._getProperty(name);
	}

	private String _getProperty(String name) {
		initProperty();
		String property = properties.getProperty(name);
		if (property == null) {
			return "";
		} else {
			return property.trim();
		}
	}

	public static Enumeration<?> propertyNames() {
		return getInstance()._propertyNames();
	}

	private Enumeration<?> _propertyNames() {
		initProperty();
		return properties.propertyNames();
	}

	private void initProperty() {
		if (properties == null) {
			synchronized (propertiesLock) {
				if (properties == null) {
					loadProperties();
				}
			}
		}
	}

	private void loadProperties() {
		properties = new Properties();
		InputStream in = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream(TEMPLATE_CONFIG_FILE);
			properties.load(in);
		} catch (Exception e) {
			System.err.println("Error reading conf properties in PropertiesUtil.loadProps() "+ e);
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 提供配置文件路径
	 * 
	 * @param filePath
	 * @return
	 */
	public Properties loadProperties(String filePath) {
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream(filePath);
			properties.load(in);
		} catch (Exception e) {
			System.err.println("Error reading conf properties in PropertiesUtil.loadProperties() "+ e);
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
		return properties;
	}
	
	/**
	 * 获取指定配置文件的指定key值
	 * 
	 * @param fileName
	 *            配置文件名称
	 * @param key
	 * @return
	 * @author ll
	 */
	public static String getData(String fileName, String key) {
		if( fileName.indexOf(".")>0){
			fileName=fileName.split("\\.")[0];
		}
		ResourceBundle resource = null;
		if (pro_map.get(fileName) == null) {
			synchronized (fileName) {
				if (pro_map.get(fileName) == null) {
					resource = ResourceBundle.getBundle(fileName);
				}
			}
		} else {
			resource = pro_map.get(fileName);
		}

		if (resource == null) {
			return null;
		}
		return resource.getString(key);

	}
	/**
	 * 获取指定配置文件全部的信息
	 * 
	 * @param fileName
	 *            配置文件名称
	 * @return Map
	 * @author ll
	 */
	public static Map<String, String> getData (String fileName) {
		if( fileName.indexOf(".")>0){
			fileName=fileName.split("\\.")[0];
		}
		HashMap<String, String> content = new HashMap<>();
		ResourceBundle resource = null;
		if (pro_map.get(fileName) == null) {
			synchronized (fileName) {
				if (pro_map.get(fileName) == null) {
					resource = ResourceBundle.getBundle(fileName);
				}
			}
		} else {
			resource = pro_map.get(fileName);
		}

		if (resource == null) {
			return null;
		}
			Set<String> keySet = resource.keySet();
			for (String key : keySet) {
				try {
					content.put(key.trim(), resource.getString(key).trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return content;
	}
}
