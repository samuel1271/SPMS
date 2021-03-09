package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.Story;
import com.revature.beans.Type;
import com.revature.exceptions.StoryAlreadyExistsException;

public interface StoryServices {
	
	public Story getStoryByID(Integer id);
	public Set<Story> getByPerson(Person p);
	public Set<Story> getAll();
	public Set<Story> getAllMinusGenre(Genre genre);
	public Set<Story> getAllByGenre(Genre g);
	public Set<Story> getAllByType(Type t);
	public Story addStory(Story st) throws StoryAlreadyExistsException;
	public void updateStory(Story st);
	public void deleteStory(Story st);
	

}
