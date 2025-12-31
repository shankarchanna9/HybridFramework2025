package com.opencart.qa.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElementException extends RuntimeException{

private static Logger log =  LogManager.getLogger(FrameworkException.class);
	
	public ElementException(String msg) {
		super(msg);
		log.info("Element Exception: "+msg);
	}
}
