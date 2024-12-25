package com.framework.constants;

import java.time.Duration;

public final class Constants {
	
	private Constants() {
	}
	
	private static final int EXPLICIT_WAIT = 5;
	private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources";
	private static final String PROPERTY_FILE_PATH = RESOURCES_PATH + "/config/";
	
	public static Duration getExplicitWait() {
		return Duration.ofSeconds(EXPLICIT_WAIT);
}
	
	public static String getPropertyFilePath() {
		return PROPERTY_FILE_PATH + "config.properties";
	}
	
	
}
