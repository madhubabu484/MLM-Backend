package com.SpringSecurity.Customgenaratorid;

public class CustomGenarator {
	
	
	public static int counter = 00100; // here value starts form 100
	
	public  static   synchronized Integer getNextId()
	{
		counter++;
		
		return counter;
		
	}

}
