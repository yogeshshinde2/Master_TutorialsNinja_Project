package com.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.framework.constants.Constants;
import com.framework.enums.ConfigProperties;
//import com.framework.exceptions.PropertyFileHandleException;

public class PropertyFileRead {
	public static String readPropertiesFiles(String key) {
		String value = new String();
		Properties p = new Properties();
		try {
			FileInputStream fis = new FileInputStream("./src/main/java/com/frameworkpractice/Config/config.properties");
			p.load(fis);
			value = p.getProperty(key);
		} catch (Exception e) {
		}
		return value;
	}
	
	private static Properties property = new Properties();
	private static final Map<String, String> CONFIG_MAP = new HashMap<String, String>();
	static {
		try {
			FileInputStream file = new FileInputStream(Constants.getPropertyFilePath());
			property.load(file);
			for (Map.Entry<Object, Object> entry : property.entrySet()) {
				CONFIG_MAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static String getPropValue(ConfigProperties key) {
		if (Objects.isNull(CONFIG_MAP.get(key.name().toLowerCase()))) {
			//throw new PropertyFileHandleException(
				//	"Property name " + key + " is not found. Please check config Properties");
		} 
		return CONFIG_MAP.get(key.name().toLowerCase());
	}
	
	

}
