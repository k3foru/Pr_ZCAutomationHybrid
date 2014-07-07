package com.rishabhSoft.selenium.zeroChaos.drivers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Abhishek Singh To read Properties files of entire Framework
 */

public class ReadObjectProperties {

	private Properties properties = new Properties();
	private File file;
	private int count = 0;
	public static String objLocatorValue;

	public void readFile() throws Exception {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(this.file));
		this.properties.load(bis);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public String getPropertyValue(String property) {
		if (count > 0) {
				return this.properties.getProperty(count + property);

		} else {
				return this.properties.getProperty(property);
		}
	}
	
	public LocatorType getLocatorType(String property) {
		String propertyValue = this.properties.getProperty(property);
		String typeIdentifier = getTypeIdentifier(propertyValue);
		for(LocatorType command : LocatorType.values()) {
			if(command.getMethodName().equals(typeIdentifier)) {
				return command;
			}
		}
		throw new IllegalArgumentException("Selected command doesn't exist");
	}
	
	private static String getTypeIdentifier(String property) {
		String[] parts = property.split("\\|");
		objLocatorValue = parts[1];
		return parts[0].trim();
	}
}
