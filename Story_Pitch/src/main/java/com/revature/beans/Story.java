package com.revature.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Story_Pitch")
public class Story {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "story_id")
	Integer id;
	@Column(name = "tent_title")
	String title;
	@ManyToOne
	@JoinColumn(name="genre_id")
	Genre genre;
	@ManyToOne
	@JoinColumn(name="status_id")
	Status status;
	@ManyToOne
	@JoinColumn(name="type_id")
	Type type;
	@ManyToOne
	@JoinColumn(name="person_id")
	Person person;
	@Column(name = "tent_comp_date")
	@Basic
	@Temporal(TemporalType.DATE)
    java.util.Date date;
	@Column(name = "tag_line")
	String tag_line;
	@Column(name = "description")
	String des;
	
	public Story() {
		
		id = 0;
		title = "";
		genre = new Genre();
		status = new Status();
		type = new Type();
		person = new Person();
		try {
			date = new SimpleDateFormat ("yyyy-MM-dd").parse("0000-00-00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tag_line = "";
		des = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTag_line() {
		return tag_line;
	}

	public void setTag_line(String tag_line) {
		this.tag_line = tag_line;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((des == null) ? 0 : des.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tag_line == null) ? 0 : tag_line.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Story other = (Story) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (des == null) {
			if (other.des != null)
				return false;
		} else if (!des.equals(other.des))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tag_line == null) {
			if (other.tag_line != null)
				return false;
		} else if (!tag_line.equals(other.tag_line))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", title=" + title + ", genre=" + genre + ", status=" + status + ", type=" + type
				+ ", person=" + person + ", date=" + date + ", tag_line=" + tag_line + ", des=" + des + "]";
	}

	

}
