package com.example.springdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

public class Sponsor {
	
	private Long id;
	private String name;
	private String about;
	private String branch;
	
	private List<Event> events = new List<Event>();
	
	
	@Entity
	@NamedQueries({
			@NamedQuery(name = "sponsor.branch", query = "Select s from Sponsor s where s.branch = :branch");
	})
	
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
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public void setBranch(String branch){
		this.branch = branch;
	}
	public String getBranch(String branch){
		return branch;
	}
	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	public List<Events> getEvents() {
		return events;
	}
	public void setEvents(List<Events> events) {
		this.events = events;
	}
}
