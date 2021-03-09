package com.revature.data;

public class DAOFactory {
	
	public static StoryDAO getStoryDAO() {
		return new StoryHibernate();
	}
	
	public static PersonDAO getPersonDAO() {
		return new PersonHibernate();
	}
	

}
