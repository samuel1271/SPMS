package com.revature.services;

import java.util.Set;
import org.apache.log4j.Logger;
import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.Story;
import com.revature.beans.Type;
import com.revature.data.DAOFactory;
import com.revature.data.StoryDAO;
import com.revature.exceptions.StoryAlreadyExistsException;

public class StoryServicesImpl implements StoryServices {
	
	private StoryDAO storyDAO;
	private static Logger log = Logger.getLogger(StoryServicesImpl.class);
	
	public StoryServicesImpl() {
		storyDAO = DAOFactory.getStoryDAO();
	}

	@Override
	public Story getStoryByID(Integer id) {
		return storyDAO.getById(id);
	}

	@Override
	public Set<Story> getByPerson(Person p) {
		return storyDAO.getByPerson(p);
	}

	@Override
	public Set<Story> getAll() {
		return storyDAO.getAll();
	}

	@Override
	public Set<Story> getAllMinusGenre(Genre genre){
		return storyDAO.getAllMinusGenre(genre);
	}

	@Override
	public Set<Story> getAllByGenre(Genre g) {
		return storyDAO.getAllByGenre(g);
	}

	@Override
	public Set<Story> getAllByType(Type t) {
		return storyDAO.getAllByType(t);
	}

	@Override
	public Story addStory(Story st) throws StoryAlreadyExistsException {
		return storyDAO.add(st);
	}

	@Override
	public void updateStory(Story st) {
		if(getStoryByID(st.getId()) != null) {
			storyDAO.update(st);
		}else {
			log.debug("Story doesn't exists!");
				
		}
		
	}

	@Override
	public void deleteStory(Story st) {
		if(getStoryByID(st.getId()) != null) {
			storyDAO.delete(st);
		}else {
			log.debug("Story doesn't exists!");
		}
		
	}

}
