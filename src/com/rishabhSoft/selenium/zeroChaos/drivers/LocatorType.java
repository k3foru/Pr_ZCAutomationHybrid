package com.rishabhSoft.selenium.zeroChaos.drivers;

import org.openqa.selenium.By;

public enum LocatorType {
	BY_ID {
		@Override
		public By execute(String id) {
			return By.id(id);
		}

		@Override
		protected String getMethodName() {
			return "id";
		}
	},
	BY_LINK_TEXT {
		@Override
		public By execute(String linkText) {
			return By.linkText(linkText);
		}

		@Override
		protected String getMethodName() {
			return "linkText";
		}
	},
	
	By_CLASSNAME{
		@Override
		public By execute(String className) {
			return By.className(className);
		}
		
		@Override
		protected String getMethodName() {
			return "className";
		}
	},
	
	BY_XPATH {
		@Override
		public By execute(String xpathExpression) {
			return By.xpath(xpathExpression);
		}

		@Override
		protected String getMethodName() {
			return "xpath";
		}
	};
	
	public abstract By execute(String selectorText);
	protected abstract String getMethodName();
}
