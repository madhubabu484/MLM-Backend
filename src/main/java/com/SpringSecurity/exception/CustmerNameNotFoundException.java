package com.SpringSecurity.exception;

@SuppressWarnings("serial")
public class CustmerNameNotFoundException extends Exception {
	
	 public CustmerNameNotFoundException(String message) {
	        super(message);  // This calls the constructor of RuntimeException
	    }
	
}
