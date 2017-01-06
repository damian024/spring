package com.example.shdemo.domain;

import com.example.shdemo.domain.Event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="Sponsor")
@Entity
@NamedQueries({
		@NamedQuery(name = "sponsor.byBranch", query = "Select s from Sponsor s where s.branch = :branch"),
		@NamedQuery(name = "sponsor.all", query = "Select s from Sponsor s"),
		@NamedQuery(name = "sponsor.byId", query = "Select s from Sponsor s where s.id = :id")
})
public class Sponsor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String about;
	private String branch;
	
	private List<Event> events = new ArrayList<Event>();
	
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
	public String getBranch(){
		return branch;
	}
	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
