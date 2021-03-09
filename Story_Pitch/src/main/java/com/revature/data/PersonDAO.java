package com.revature.data;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.exceptions.EmailAlreadyExistsException;

public interface PersonDAO extends GenericDAO<Person>{
	
	public Person add(Person p) throws EmailAlreadyExistsException;
	public Person getByEmail(String email);
	public Set<Person> getAllAuthors();
	public Set<Person> getAllEditors();
	public Set<Person> getSpecificEditors(Integer id);

}
