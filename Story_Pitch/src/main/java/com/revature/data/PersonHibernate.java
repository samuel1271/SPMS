package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.revature.beans.Person;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.utils.HibernateUtil;

public class PersonHibernate implements PersonDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Person add(Person p) throws EmailAlreadyExistsException {
		
		Session s = hu.getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			s.save(p);
			tx.commit();
			
		}catch(Exception e) {
			if (tx != null) tx.rollback();
		}finally {
			s.close();
		}
		return p;
	}

	@Override
	public Person getById(Integer id) {
		Session s = hu.getSession();
		Person p = s.get(Person.class, id);
		s.close();
		return p;
	}

	@Override
	public Set<Person> getAll() {
		Session s = hu.getSession();
		String query = "FROM Person";
		Query<Person> q = s.createQuery(query, Person.class);
		List<Person> personList = q.getResultList();
		Set<Person> People = new HashSet<>();
		People.addAll(personList);
		s.close();
		return People;
	}

	@Override
	public void delete(Person p) {
		
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void update(Person p) {
		
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public Person getByEmail(String email) {
		Session s = hu.getSession();
		String query = "From Person WHERE email = :email";
		Query<Person> q = s.createQuery(query, Person.class);
		q.setParameter("email", email);
		Person p = q.getSingleResult();
		s.close();
		return p;
	}

	@Override
	public Set<Person> getAllAuthors() {
		Session s = hu.getSession();
		String query = "FROM Person WHERE role.id = 4";
		Query<Person> q = s.createQuery(query, Person.class);
		List<Person> personList = q.getResultList();
		Set<Person> People = new HashSet<>();
		People.addAll(personList);
		s.close();
		return People;
	}

	@Override
	public Set<Person> getAllEditors() {
		Session s = hu.getSession();
		String query = "FROM Person WHERE role.id != 4";
		Query<Person> q = s.createQuery(query, Person.class);
		List<Person> personList = q.getResultList();
		Set<Person> People = new HashSet<>();
		People.addAll(personList);
		s.close();
		return People;
	}

	@Override
	public Set<Person> getSpecificEditors(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Person WHERE role.id = :id";
		Query<Person> q = s.createQuery(query, Person.class);
		q.setParameter("id", id);
		List<Person> personList = q.getResultList();
		Set<Person> People = new HashSet<>();
		People.addAll(personList);
		s.close();
		return People;
	}

}
