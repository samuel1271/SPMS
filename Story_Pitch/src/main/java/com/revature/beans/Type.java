package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="story_type")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="type_id")
	Integer id;
	String type_name;
	Integer weight;
	
	//1. Novel = 50, 2. Novella = 25, 3. Short Story = 20, 4. Article = 10
	
	public Type() {
		id = 0;
		type_name = "";
		weight = 0;
	}
	public Type(String s) {
		
		switch(s) {
		case "Novel":
			id = 1;
			type_name = "Novel";
			weight = 50;
			break;
		case "Novella":
			id = 2;
			type_name = "Novella";
			weight = 25;
			break;
		case "Short Story":
			id = 3;
			type_name = "Short Story";
			weight = 20;
			break;
		case "Article":
			id = 4;
			type_name = "Article";
			weight = 10;
			break;
		default:
			System.out.println("This is not a type");
			break;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type_name == null) ? 0 : type_name.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Type other = (Type) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type_name == null) {
			if (other.type_name != null)
				return false;
		} else if (!type_name.equals(other.type_name))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", type_name=" + type_name + ", weight=" + weight + "]";
	}
	
	
	
}
