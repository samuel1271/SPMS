package com.revature.exceptions;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends Exception{
	public EmailAlreadyExistsException() {
		super("This email already exists!");
	}

}
