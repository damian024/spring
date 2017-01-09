package com.example.shdemo.service;

import java.util.List;
import com.example.shdemo.domain.Event;
import com.example.shdemo.domain.Sponsor;

public interface SponsorManager2 { 
	
	void addEvent(Event event);
	List<Event> getAllEvents();
	Event findEventById(Long id);	
	void editEvent(Event event);
	void deleteEvent(Event event);
	List<Event> findEventByAbout(String name);
	
	void addEvents();
	void addSponsors();
	
	void addSponsor(Sponsor sponsor);
	List<Sponsor> getAllSponsors();
	Sponsor findSponsorById(Long id);
	void editSponsor(Sponsor sponsor);
	void deleteSponsor(Sponsor sponsor);
	List<Sponsor> findSponsorByBranch(String branch);
   //komentarz
	List<Event> getOwnedEvents(Sponsor sponsor);
}