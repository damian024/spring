package com.example.springdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = "events.charity", query = "Select e from Events e where e.charity = true"),
		@NamedQuery(name = "events.all", query = "Select e from Events e"),
		@NamedQuery(name = "events.byId", query = "Select e from Events e where e.id = :id"),
		@NamedQuery(name = "events.byName", query = "Select e from Events e where e.name = :name")
})

public class Event {
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
