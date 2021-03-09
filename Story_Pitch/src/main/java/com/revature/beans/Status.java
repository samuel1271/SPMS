package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="status_id")
	Integer id;
	String status_name;
	
	//1. Pending, 2. On Hold, 3. Approved
	
	public Status() {
		id = 0;
		status_name = "";
	}
	
	public Status(String s) {
		switch(s) {
		case "Pending":
			id = 1;
			status_name = "Pending";
			break;
		case "On Hold":
			id = 2;
			status_name = "On Hold";
			break;
		case "Approved":
			id = 3;
			status_name = "Approved";
			break;
		default:
			System.out.println("This is not a status");
			break;
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status_name == null) ? 0 : status_name.hashCode());
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
		Status other = (Status) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status_name == null) {
			if (other.status_name != null)
				return false;
		} else if (!status_name.equals(other.status_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", status_name=" + status_name + "]";
	}
	
	

}
