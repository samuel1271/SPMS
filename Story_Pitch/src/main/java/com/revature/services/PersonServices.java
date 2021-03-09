package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.exceptions.EmailAlreadyExistsException;

public interface PersonServices {

	public Person addPerson(Person p) throws EmailAlreadyExistsException;
	public Person getPersonById(Integer id);
	public Person getPersonByEmail(String email);
	public Set<Person> getAllPerson();
	public Set<Person> getAllAuthors();
	public Set<Person> getAllEditors();
	public Set<Person> getSpecificEditors(Integer id);
	public void deletePerson(Person p);
	public void updatePerson(Person p);
}
