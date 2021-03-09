package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Person;
import com.revature.data.DAOFactory;
import com.revature.data.PersonDAO;
import com.revature.exceptions.EmailAlreadyExistsException;

public class PersonServicesImpl implements PersonServices{
	
	private PersonDAO personDAO;
	private static Logger log = Logger.getLogger(PersonServicesImpl.class);

	public PersonServicesImpl(){
		personDAO = DAOFactory.getPersonDAO();
	}
	
	@Override
	public Person addPerson(Person p) throws EmailAlreadyExistsException {
		System.out.println("Services");
		return personDAO.add(p);
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDAO.getById(id);
	}

	@Override
	public Person getPersonByEmail(String email) {
		return personDAO.getByEmail(email);
	}

	@Override
	public Set<Person> getAllPerson() {
		return personDAO.getAll();
	}

	@Override
	public Set<Person> getAllAuthors() {
		return personDAO.getAllAuthors();
	}

	@Override
	public Set<Person> getAllEditors() {
		return personDAO.getAllEditors();
	}

	@Override
	public Set<Person> getSpecificEditors(Integer id) {
		return personDAO.getSpecificEditors(id);
	}

	@Override
	public void deletePerson(Person p) {

		if(getPersonById(p.getId()) != null) {
			personDAO.delete(p);
		}else {
			log.debug("Person doesn't exists!");
		}
		
	}

	@Override
	public void updatePerson(Person p) {
		
		if(getPersonById(p.getId()) != null) {
			personDAO.update(p);
		}else {
			log.debug("Person doesn't exists!");
		}
	}
}
