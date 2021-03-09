package com.revature.data;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.Story;
import com.revature.beans.Type;
import com.revature.exceptions.StoryAlreadyExistsException;

public interface StoryDAO extends GenericDAO<Story>{
	
	public Story add(Story st) throws StoryAlreadyExistsException;
	public Set<Story> getByPerson(Person p);
	public Set<Story> getAllMinusGenre(Genre genre);
	public Set<Story> getAllByGenre(Genre genre);
	public Set<Story> getAllByType(Type type);

}
