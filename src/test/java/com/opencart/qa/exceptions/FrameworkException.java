package com.opencart.qa.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FrameworkException extends RuntimeException {
	private static Logger log =  LogManager.getLogger(FrameworkException.class);
	
	public FrameworkException(String msg) {
		super(msg);
		log.info("Framework Exception: "+msg);
	}
}
