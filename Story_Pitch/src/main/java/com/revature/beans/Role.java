package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person_role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	Integer id;
	String role_name;
	
	//1.Assistant Editor, 2.General Editor, 3. Senior Editor, 4. Author
	
	public Role() {
		id = 0;
		role_name = "";
	}
	
	public Role(String s) {
		switch(s) {
		case "Assistant Editor":
			id = 1;
			role_name = "Assistant Editor";
			break;
		case "General Editor":
			id = 2;
			role_name = "General Editor";
			break;
		case "Senior Editor":
			id = 3;
			role_name = "Senior Editor";
			break;
		case "Author":
			id = 4;
			role_name = "Author";
			break;
		default:
			System.out.println("This is not a role");
			break;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role_name == null) ? 0 : role_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role_name == null) {
			if (other.role_name != null)
				return false;
		} else if (!role_name.equals(other.role_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role_name=" + role_name + "]";
	}

	
}
