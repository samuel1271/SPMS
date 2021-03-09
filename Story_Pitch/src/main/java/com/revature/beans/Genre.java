package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="genre_id")
	Integer id;
	String genre_name;
	
	public Genre() {
		id = 0;
		genre_name = "";
	}
	
	public Genre(String s) {
		switch(s) {
		case "Horror":
			id = 1;
			genre_name = "Horror";
			break;
		case "Comedy":
			id = 2;
			genre_name = "Comedy";
			break;
		case "Drama":
			id = 3;
			genre_name = "Drama";
			break;
		case "Action":
			id = 4;
			genre_name = "Action";
			break;
		default:
			id = 5;
			genre_name = "Other";
			break;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenre_name() {
		return genre_name;
	}

	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre_name == null) ? 0 : genre_name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Genre other = (Genre) obj;
		if (genre_name == null) {
			if (other.genre_name != null)
				return false;
		} else if (!genre_name.equals(other.genre_name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", genre_name=" + genre_name + "]";
	}
	
	

}
