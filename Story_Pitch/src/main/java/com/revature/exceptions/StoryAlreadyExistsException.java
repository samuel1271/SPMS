package com.revature.exceptions;

@SuppressWarnings("serial")
public class StoryAlreadyExistsException extends Exception{
	public StoryAlreadyExistsException() {
		super("This story already exists!");
	}

}
