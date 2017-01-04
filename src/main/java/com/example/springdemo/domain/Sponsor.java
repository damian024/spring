package com.example.springdemo.domain;

public class Sponsor {
	
	private int id;
	private String name;
	private String about;
	
	public Sponsor(String name, String about)
	{
		this.name = name;
		this.about = about;
	}
	public Sponsor(){
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
