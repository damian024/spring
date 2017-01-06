package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name="Event")
@Entity
@NamedQueries({
		@NamedQuery(name = "event.charity", query = "Select e from Event e where e.charity = true"),
		@NamedQuery(name = "event.all", query = "Select e from Event e"),
		@NamedQuery(name = "event.byId", query = "Select e from Event e where e.id = :id"),
		@NamedQuery(name = "event.byName", query = "Select e from Event e where e.name = :name"),
		@NamedQuery(name = "event.byAbout", query = "Select e from Event e where e.about = :about")
})

public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String about;
	private Boolean charity;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getCharity() {
		return charity;
	}
	public void setCharity(Boolean charity) {
		this.charity = charity;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
}
