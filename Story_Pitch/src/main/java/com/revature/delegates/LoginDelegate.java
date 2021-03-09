package com.revature.delegates;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Person;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.services.PersonServices;
import com.revature.services.PersonServicesImpl;

public class LoginDelegate implements FrontControllerDelegate{
	
	private PersonServices perServ = new PersonServicesImpl();
	private ObjectMapper om = new ObjectMapper();

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		Person p = new Person();
		
		if (path == null || path.equals("")) {
			if ("POST".equals(req.getMethod())) {
				// register a user
				p.setFirstname(req.getParameter("firstname"));
				p.setLastname(req.getParameter("lastname"));
				p.setEmail(req.getParameter("email"));
				p.setPasswd(req.getParameter("passwd"));
				p.setGenre(null);
				p.setWeight(0);
				p.getRole().setId(4);
					//p = (Person) om.readValue(req.getInputStream(), Person.class);
				try {
					p = (perServ.addPerson(p));
					req.getSession().setAttribute("person", p);
				} catch (EmailAlreadyExistsException e) {
					resp.sendError(HttpServletResponse.SC_CONFLICT, "Email already exists");
				}
				if (p.getId() == 0) {
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				} else {
					resp.getWriter().write(om.writeValueAsString(p));
					resp.setStatus(HttpServletResponse.SC_CREATED);
				}
			} else if("GET".equals(req.getMethod())){
				p = (Person) req.getSession().getAttribute("person");
				resp.getWriter().write(om.writeValueAsString(p));
				resp.setStatus(200);
				
			} else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else if (path.contains("login")) {
			if ("POST".equals(req.getMethod()))
				logIn(req, resp);
			else if ("DELETE".equals(req.getMethod())) {
				req.getSession().invalidate();
			}else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else {
			userWithId(req, resp, Integer.valueOf(path));
		}
	}
	
	private void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		
		Person p = perServ.getPersonByEmail(email);
		if (p != null) {
			if (p.getPasswd().equals(password)) {
				req.getSession().setAttribute("person", p);
				resp.getWriter().write(om.writeValueAsString(p));
			} else {
				resp.sendError(404, "Incorrect password.");
			}
		} else {
			resp.sendError(404, "No user found with that email.");
		}
		
		System.out.println(p);
	}
	
	private void userWithId(HttpServletRequest req, HttpServletResponse resp, Integer id) throws IOException {
		switch (req.getMethod()) {
			case "GET":
				Person p = perServ.getPersonById(id);
				if (p != null) {
					resp.getWriter().write(om.writeValueAsString(p));
				} else {
					resp.sendError(404, "Person not found.");
				}
				break;
			case "PUT":
				//String password = req.getParameter("pass");
				Person person = (Person) req.getSession().getAttribute("person");
				if (person != null) {
					//person.setPasswd(password);
					perServ.updatePerson(person);
				} else
					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				break;
			case "DELETE":
				Person user = om.readValue(req.getInputStream(), Person.class);
				perServ.deletePerson(user);
				break;
			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
		}
	}

}
