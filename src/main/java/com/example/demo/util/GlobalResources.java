package com.example.demo.util;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class GlobalResources {

	
	public static Logger getLogger(Class className) {
		return LoggerFactory.getLogger(className);
		
	}
}
