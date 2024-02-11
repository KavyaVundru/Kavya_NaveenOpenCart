package com.qa.opencart.exception;

public class FrameWorkException extends RuntimeException{
	
	public FrameWorkException(String msg)
	{
		System.out.println("Excpetion is "+msg);
	}

}
