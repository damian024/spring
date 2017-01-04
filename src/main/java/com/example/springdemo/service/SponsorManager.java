package com.example.springdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Event;
import com.example.shdemo.domain.Sponsor;


@Component
@Transactional
public class SponsorManagerHibernate{
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addEvents(){
		int i;
		for(i=1;i<7;i++){
			Event event = new Event();
			String name = "event nr "+ i;
			String about = "Jakis opis" + i;
			Boolean charity =(i%2==0)? true: false;
			event.setName(nazwa);
			event.setAbout(about);
			event.setcharity(charity);
			addEvent(event);
		}		
	}	
	
	@Override
	public void addSponsors(){
		int i,j;
		
			for(i=1;i<4;i++){		
				List<Event> events = new ArrayList<Event>();
				
				for(j=3;j<7;j++){
					Event event = new Event();
					String name = "event nr "+ i;
					String about = "Jakis opis" + i;
					Boolean charity =(i%2==0)? true: false;
					event.setName(nazwa);
					event.setAbout(about);
					event.setcharity(charity);
					events.add(event);
					addEvent(event);
				}
				Sponsor sponsor = new Sponsor();	
				String name = "sponsor nr "+ i;
				String about = "Jakis opis" + i;
				String branch = (i%2==0) ? "car": "fuel";
				sponsor.setName(nazwa);
				sponsor.setAbout(about);
				sponsor.setBreanch(branch);
				sponsor.setEvents(event);
				addSponsor(sponsor);
			}	
	}
	
	
	@Override
	public void addEvent(Event event) {
		event.setId(null);
		sessionFactory.getCurrentSession().persist(event);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getAllEvents() {
		return sessionFactory.getCurrentSession().getNamedQuery("event.all")
				.list();
	}
	
	@Override
	public Event findEventById(Long id) {
		return (Event) sessionFactory.getCurrentSession().getNamedQuery("event.byId").setLong("id", id).uniqueResult();
	}
	
	@Override
	public void editEvent(Event event)
	{
		if(event.getId() != null){
			sessionFactory.getCurrentSession().persist(event);
		}
	}
	
	@Override
	public void deleteEvent(Event event) {
		event = (Event) sessionFactory.getCurrentSession().get(Event.class,
				event.getId());
	
		sessionFactory.getCurrentSession().delete(event);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Event> findEventByName(String name) {
		return sessionFactory.getCurrentSession().getNamedQuery("event.byName").setInteger("name", name).list();
	}
	
	

	
	
	@Override
	public void addSponsor(Sponsor sponsor) {
		sponsor.setId(null);
		sessionFactory.getCurrentSession().persist(sponsor);
	}
	
	@Override
	public void deleteSponsor(Sponsor sponsor) {
		sponsor = (Sponsor) sessionFactory.getCurrentSession().get(Sponsor.class,sponsor.getId());
	// lazy loading here
		for (Event event : sponsor.getEvents()) {
			//sessionFactory.getCurrentSession().delete(event);
			/*car.setSold(false);
			sessionFactory.getCurrentSession().update(car);*/
		}
		
		sessionFactory.getCurrentSession().delete(sponsor);
	}
	
	@Override
	public List<Event> getOwnedEvents(Sponsor sponsor) {
		sponsor = (Sponsor) sessionFactory.getCurrentSession().get(Sponsor.class,
				sponsor.getId());
	// lazy loading here - try this code without (shallow) copying
		List<Event> event = new ArrayList<Event>(sponsor.getEvents());
		return events;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Sponsor> getAllESponsors() {
		return sessionFactory.getCurrentSession().getNamedQuery("sponsor.all")
				.list();
	}
	
	@Override
	public Sponsor findSponsorById(Long id) {
		return (Sponsor) sessionFactory.getCurrentSession().getNamedQuery("sponsor.byId").setLong("id", id).uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Sponsor> findSponsorByBranch(String branch) {
		return sessionFactory.getCurrentSession().getNamedQuery("sponsor.byBranch").setInteger("branch", branch).list();
	}
	
	@Override
	public void editSponsor(Sponsor sponsor)
	{
		if(sponsor.getId() != null){
			sessionFactory.getCurrentSession().persist(sponsor);
		}
	}
}