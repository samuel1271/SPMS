package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.Story;
import com.revature.beans.Type;
import com.revature.exceptions.StoryAlreadyExistsException;
import com.revature.utils.HibernateUtil;

public class StoryHibernate implements StoryDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Story add(Story st) throws StoryAlreadyExistsException {
		Session s = hu.getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			s.save(st);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if (tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
		return st;
	}

	@Override
	public Story getById(Integer id) {
		Session s = hu.getSession();
		Story st = s.get(Story.class, id);
		s.close();
		return st;
	}
	
	@Override
	public Set<Story> getByPerson(Person p) {
		Session s = hu.getSession();
		String query = "FROM Story WHERE person.id = :personID";
		Query<Story> q = s.createQuery(query, Story.class);
		q.setParameter("personID" , p.getId());
		List<Story> storyList = q.getResultList();
		Set<Story> stories = new HashSet<>();
		stories.addAll(storyList);
		s.close();
		return stories;
	}

	@Override
	public Set<Story> getAll() {
		Session s = hu.getSession();
		String query = "FROM Story where status.id = 3";
		Query<Story> q = s.createQuery(query, Story.class);
		List<Story> storyList = q.getResultList();
		Set<Story> stories = new HashSet<>();
		stories.addAll(storyList);
		s.close();
		return stories;
	}

	@Override
	public void delete(Story st) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(st);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void update(Story st) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(st);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public Set<Story> getAllMinusGenre(Genre genre) {
		Session s = hu.getSession();
		String query = "FROM Story WHERE genre.id != :genre";
		Query<Story> q = s.createQuery(query, Story.class);
		q.setParameter("genre", genre.getId());
		List<Story> storyList = q.getResultList();
		Set<Story> stories = new HashSet<>();
		stories.addAll(storyList);
		s.close();
		return stories;
	}

	@Override
	public Set<Story> getAllByGenre(Genre genre) {
		
		Session s = hu.getSession();
		String query = "FROM Story WHERE genre.id = :genre";
		Query<Story> q = s.createQuery(query, Story.class);
		q.setParameter("genre", genre.getId());
		List<Story> storyList = q.getResultList();
		Set<Story> stories = new HashSet<>();
		stories.addAll(storyList);
		s.close();
		return stories;
	}

	@Override
	public Set<Story> getAllByType(Type type) {
		Session s = hu.getSession();
		String query = "FROM Story WHERE type.id = :type";
		Query<Story> q = s.createQuery(query, Story.class);
		q.setParameter("type", type.getId());
		List<Story> storyList = q.getResultList();
		Set<Story> stories = new HashSet<>();
		stories.addAll(storyList);
		s.close();
		return stories;
	}

}
