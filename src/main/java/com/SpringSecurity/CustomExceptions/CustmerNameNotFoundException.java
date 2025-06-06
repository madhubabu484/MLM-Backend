package com.SpringSecurity.CustomExceptions;

public class CustmerNameNotFoundException extends Exception {
	
	 public CustmerNameNotFoundException(String message) {
	        super(message);  // This calls the constructor of RuntimeException
	    }
	
}
