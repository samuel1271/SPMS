package com.revature.delegates;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Person;
import com.revature.beans.Story;
import com.revature.exceptions.StoryAlreadyExistsException;
import com.revature.services.PersonServices;
import com.revature.services.PersonServicesImpl;
import com.revature.services.StoryServices;
import com.revature.services.StoryServicesImpl;

public class StoryDelegate implements FrontControllerDelegate{
	private StoryServices storyServ = new StoryServicesImpl();
	private PersonServices perServ = new PersonServicesImpl(); 
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if (path.contains("submit")) {
			switch(req.getMethod()) {
			case "POST":
				
				Story st = (Story) om.readValue(req.getInputStream(), Story.class);
				Person p = st.getPerson();
	
				try {
					req.getSession().setAttribute("person",p);
					perServ.updatePerson(p);
					st = (storyServ.addStory(st)); //add story
				} catch (StoryAlreadyExistsException e) {
					resp.sendError(HttpServletResponse.SC_CONFLICT, "Story already exists");
				}
				resp.getWriter().write(om.writeValueAsString(st));
				resp.setStatus(HttpServletResponse.SC_CREATED);
				
				break;
			default:
				
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				
				break;
			}
		}else{
			Person p = null;
			Story st = null;
			
			switch(req.getMethod()){
				case "GET":
					if(path.contains("byGenre")) {
						String id = req.getParameter("id");
						p = perServ.getPersonById(Integer.parseInt(id));
						
						if (p.getRole().getId() == 2) {
							resp.getWriter().write(om.writeValueAsString(storyServ.getAllMinusGenre(p.getGenre())));
						}else {
							resp.getWriter().write(om.writeValueAsString(storyServ.getAllByGenre(p.getGenre())));
						}
					}else if (path.contains("oneStory")){
						String id = req.getParameter("id");
						resp.getWriter().write(om.writeValueAsString(storyServ.getStoryByID(Integer.parseInt(id))));	
					}else if (path.contains("byPerson")) {
							String id = req.getParameter("id");
							p = perServ.getPersonById(Integer.parseInt(id));
							resp.getWriter().write(om.writeValueAsString(storyServ.getByPerson(p)));
					}else if (path.contains("viewAll")){
						resp.getWriter().write(om.writeValueAsString(storyServ.getAll()));
					}
					break;
				case "PUT":
					p = (Person) req.getSession().getAttribute("person");
					if (isEditor(p)) {	
						st = om.readValue(req.getInputStream(), Story.class);
						
						//update weight
						Story st1= storyServ.getStoryByID(st.getId());
						if(st1.getStatus() != st.getStatus()) {
							p = st.getPerson();
							p.setWeight(p.getWeight() - st.getType().getWeight());
							perServ.updatePerson(p);
						}
						
						
						//update story
						storyServ.updateStory(st);
						resp.getWriter().write(om.writeValueAsString(st));
					} else {
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					}
					break;
				case "DELETE":
					p = (Person) req.getSession().getAttribute("person");
					if (isEditor(p)) {
						st = om.readValue(req.getInputStream(), Story.class);
						
						//update weight
						p = st.getPerson();
						p.setWeight(p.getWeight() - st.getType().getWeight());
						perServ.updatePerson(p);
						//delete story
						storyServ.deleteStory(st);
					}else {
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					}
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;						
				}
			}		
		}

	private boolean isEditor(Person p){
		if(p != null && p.getRole().getId() != 4) {
			
			return true;
		}else {
			return false;
		}	
	}
}
