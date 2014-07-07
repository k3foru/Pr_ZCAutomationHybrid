package com.rishabhSoft.selenium.zeroChaos.drivers;

import java.io.File;

public class SetObjectProperties {
	
	// create reference of ReadProperties class.
	public static ReadObjectProperties appConfig;
	public static ReadObjectProperties timesheet;
	public static ReadObjectProperties common;
	public static ReadObjectProperties resource;
	public static ReadObjectProperties contract;
	public static ReadObjectProperties project;
	public static ReadObjectProperties customer;

	public SetObjectProperties() throws Exception {
		
		// create instance of ReadProperties class.
		appConfig = new ReadObjectProperties();
		timesheet = new ReadObjectProperties();
		common = new ReadObjectProperties();
		resource = new ReadObjectProperties();
		contract = new ReadObjectProperties();
		project = new ReadObjectProperties();
		customer=new ReadObjectProperties();
		
		// Read AppConfig properties file
		appConfig.setFile(new File("Config/AppConfig.properties"));
		appConfig.readFile();

		// Read Timesheet properties file
		timesheet.setFile(new File("ObjectRepository/OR_TimeSheet.properties"));
		timesheet.readFile();

		// Read Common properties file
		common.setFile(new File("ObjectRepository/OR_Common.properties"));
		common.readFile();
		
		// Read Resource properties file
		resource.setFile(new File("ObjectRepository/OR_Resource.properties"));
		resource.readFile();
		
		// Read Contract properties file
		contract.setFile(new File("ObjectRepository/OR_Contract.properties"));
		contract.readFile();
		
		// Read Contract properties file
		project.setFile(new File("ObjectRepository/OR_Project.properties"));
		project.readFile();
		
		// Read Resource properties file
		customer.setFile(new File("ObjectRepository/OR_Customer.properties"));
		customer.readFile();
	}
	
	public static ReadObjectProperties getObjectReference(String objReference){
		
		if(objReference.equals("timesheet"))
			return timesheet;
		else if (objReference.equals("common")) {
			return common;
		}
		else if (objReference.equals("resource")) {
			return resource;
		}
		else if (objReference.equals("contract")) {
			return contract;
		}
		else if (objReference.equals("project")) {
			return project;
		}
		
		throw new IllegalArgumentException("Selected object doesn't exist");
	}
}
