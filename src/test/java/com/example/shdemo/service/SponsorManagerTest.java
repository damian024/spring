package com.example.shdemo.service;

import com.example.shdemo.domain.Event;
import com.example.shdemo.domain.Sponsor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SponsorManagerTest {
	
	@Autowired
	SponsorManager sponsorManager;
	
	private final String name1 = "Orkiestra swiatecznej pomocy";
	private final String name2 = "Mecz charytatywny";
	
	private final String about1 = "Zakup sprzetu medycznego";
	private final String about2 = "Zbior pieniedzy na leczenie";
	
	private final boolean charity1 = true;
	private final boolean charity2 = true;
	
	
	//Sponsor
	private final String nameU1 = "Play";
	private final String nameU2 = "Polpharma";
	
	private final String aboutU2 = "Firma prodspujaca wiele popularnych lekow";
	private final String aboutU1 = "Jedna z najwiekszych firm wsrod operatorow telefonow komirkowych";
	private final String aboutUnique = "Firma prodspujaca leki na raka";
	
	private final String branchU1 = "Telekomunikacja";
	private final String branchU2 = "Farmacja";
	

	
	@Test
	public void addEventCheck() {
		
		sponsorManager.addEvents();
		
		Event event = new Event();
		event.setName(name1);
		event.setAbout(about1);
		event.setCharity(charity1);
		
		List<Event> events = sponsorManager.getAllEvents();
		int eventsBefore = events.size();
		//System.out.println(eventsCount);
		
		sponsorManager.addEvent(event);
		
		List<Event> events2 = sponsorManager.getAllEvents();
		int eventsAfter = events.size();
		
		for (Event eventPetla : events) {
			if (eventPetla.getId().equals(event.getId())) {
				assertEquals(name1,eventPetla.getName());
				assertEquals(about1,eventPetla.getAbout());
				assertEquals(charity1,eventPetla.getCharity());
			}
		}
		
		assertEquals(eventsBefore+1, eventsAfter);
	
	}
	
	
/*	@Test
	public void deleteEventCheck() {
		
		sponsorManager.addEvents();
		Event event = new Event();
		event.setName(name1);
		event.setAbout(about1);
		event.setCharity(charity1);
		sponsorManager.addEvent(event);
		List<Event> events = sponsorManager.getAllEvents();
		int eventsBefore = events.size();
		
		sponsorManager.deleteEvent(event);
		
		List<Event> events2 = sponsorManager.getAllEvents();
		int eventsAfter = events.size();
		
		assertEquals(eventsBefore, eventsAfter+1);
		
		assertEquals(null, sponsorManager.findEventById(event.getId()));
		
	}
	
	@Test
	public void findEventCheck() {
		sponsorManager.addEvents();
		List<Event> events = sponsorManager.getAllEvents();
		int eventsCount = events.size();
		
		Event event = new Event();
		event.setName(name1);
		event.setAbout(about1);
		event.setCharity(charity1);
		
		sponsorManager.addEvent(event);
		assertNotNull(sponsorManager.findEventById(event.getId()));
		
		sponsorManager.deleteEvent(event);
		assertNull(sponsorManager.findEventById(event.getId()));
		
		List<Event> events2 = sponsorManager.getAllEvents();
		int eventsCount2 = events2.size();
		
		assertEquals(eventsCount, eventsCount2);
	}
	
	@Test
		public void findAllEventsCheck() {
		
		sponsorManager.addEvents();
		
		Event event1 = new Event();
		event1.setName(name2);
		event1.setAbout(about2);
		event1.setCharity(charity2);
		
		List<Event> events = sponsorManager.getAllEvents();
		int eventsCount = events.size();
		
		Event event2 = new Event();
		event2.setName(name2);
		event2.setAbout(about2);
		event2.setCharity(charity2);
		
		sponsorManager.addEvent(event1);
		sponsorManager.addEvent(event2);
		
		List<Event> events2 = sponsorManager.getAllEvents();
		int eventsCount2 = events2.size();
		
		assertNotNull(events2);
		assertEquals(eventsCount2, eventsCount+2);
	}
	
	
	@Test
	public void editEventCheck() {
		
		sponsorManager.addEvents();
		List<Event> events = sponsorManager.getAllEvents();
		int eventsCount = events.size();
		
		Event event1 = new Event();
		event1.setName(name2);
		event1.setAbout(about2);
		event1.setCharity(charity2);
		
		Event event2 = new Event();
		event2.setName(name2);
		event2.setAbout(about2);
		event2.setCharity(charity2);
		
		Event event3 = new Event();
		event3.setName(name2);
		event3.setAbout(about2);
		event3.setCharity(charity2);
		
		sponsorManager.addEvent(event1);
		sponsorManager.addEvent(event2);
		sponsorManager.addEvent(event3);
		
		List<Event> events2 = sponsorManager.getAllEvents();
		int eventsCount2 = events2.size();
		assertEquals(eventsCount+3, eventsCount2);
		
		event2.setName(name2);
		event2.setAbout(about1);
		event2.setCharity(charity1);
		sponsorManager.editEvent(event2);
		
		Long idEvent = event2.getId();
		Event edited = sponsorManager.findEventById(idEvent);
		
		assertEquals(name2, edited.getName());
		assertEquals(about1,edited.getAbout());
		assertEquals(charity1, edited.getCharity());
		
		idEvent = event1.getId();
		edited = sponsorManager.findEventById(idEvent);
		
		assertEquals(name2, edited.getName());
		assertEquals(about2, edited.getAbout());
		assertEquals(charity2, edited.getCharity());
		
		idEvent = event2.getId();
		edited = sponsorManager.findEventById(idEvent);
		
		assertEquals(name2, edited.getName());
		assertEquals(about2, edited.getAbout());
		assertEquals(charity2, edited.getCharity());
	}
	
	@Test
	public void findEventByAboutCheck() {
		
		sponsorManager.addEvents();
		List<Event> events = sponsorManager.getAllEvents();
		int eventsCount = events.size();
		
		Event event = new Event();
		event.setName(name1);
		event.setAbout(aboutUnique);
		event.setCharity(charity1);
		
		sponsorManager.addEvent(event);
		assertNotNull(sponsorManager.findEventById(event.getId()));
		
		List<Event> events2 = sponsorManager.getAllEvents();
		int eventsCount2 = events2.size();
		
		assertEquals(eventsCount2, eventsCount+1);
		
		assertNotNull(sponsorManager.findEventByAbout(event.getAbout()));
		List<Event> evList = sponsorManager.findEventByAbout(event.getAbout());
		int found = 0;
		for(Event ev : evList)
		{
			if(ev.getId() == event.getId())
			{
				assertEquals(ev.getAbout(),event.getAbout());
				found++;
			}
		}
		assertEquals(found,1);
	}
	
	
	//Testowanie Sponsorow
	
	@Test
	public void addSponsorCheck() {
		
		sponsorManager.addSponsors();
		
		Event event1 = new Event();
		event1.setName(name2);
		event1.setAbout(about2);
		event1.setCharity(charity2);
		sponsorManager.addEvent(event1);
		List<Event> eventsTab = new ArrayList<Event>();
		eventsTab.add(event1);
		
		Sponsor sponsor = new Sponsor();
		sponsor.setName(nameU1);
		sponsor.setAbout(about1);
		sponsor.setEvents(eventsTab);
		
		List<Sponsor> sponsors = sponsorManager.getAllSponsors();
		int sponsorsCount = sponsors.size();
		
		sponsorManager.addSponsor(sponsor);
		
		List<Sponsor> sponsors2 = sponsorManager.getAllSponsors();
		int sponsorsCount2 = sponsors2.size();
		
		Long id = sponsor.getId();
		Sponsor sp = sponsorManager.findSponsorById(id);
		
		assertEquals(sp.getName(), sponsor.getName());
		assertEquals(sp.getAbout(), sponsor.getAbout());
		assertEquals(sp.getBranch(), sponsor.getBranch());
		
		assertEquals(sponsorsCount+1, sponsorsCount2);
		
		List<Event> eventsOfSponsor = new ArrayList<Event>();
		eventsOfSponsor = sp.getEvents();
		Event ev = eventsOfSponsor.get(0);
		assertEquals(event1.getName(), ev.getName());
		assertEquals(event1.getAbout(), ev.getAbout());
		assertEquals(event1.getCharity(), ev.getCharity());
	
	}
	
	
	@Test
	public void deleteSponsorCheck() {
		
		sponsorManager.addSponsors();
		
		Event event1 = new Event();
		event1.setName(name1);
		event1.setAbout(about1);
		event1.setCharity(charity1);
		sponsorManager.addEvent(event1);
		
		Event event2 = new Event();
		event2.setName(name1);
		event2.setAbout(about1);
		event2.setCharity(charity1);
		sponsorManager.addEvent(event2);
		
		List<Event> eventsTab = new ArrayList<Event>();
		eventsTab.add(event1);
		eventsTab.add(event2);
		
		Sponsor sponsor = new Sponsor();
		sponsor.setName(nameU1);
		sponsor.setAbout(about1);
		sponsor.setEvents(eventsTab);
		sponsorManager.addSponsor(sponsor);
		
		List<Sponsor> sponsors = sponsorManager.getAllSponsors();
		int sponsorsCount = sponsors.size();
		
		sponsorManager.deleteSponsor(sponsor);
		
		List<Sponsor> sponsors2 = sponsorManager.getAllSponsors();
		int sponsorsCount2 = sponsors2.size();
		
		assertEquals(sponsorsCount, sponsorsCount2+1);
		assertEquals(null, sponsorManager.findSponsorById(sponsor.getId()));
		
		assertNotNull(sponsorManager.findEventById(event1.getId()));
		assertNotNull(sponsorManager.findEventById(event2.getId()));
	}
	
	
	@Test
	public void findSponsorCheck() {
		
		sponsorManager.addSponsors();
		
		List<Sponsor> sponsors = sponsorManager.getAllSponsors();
		int sponsorsCount = sponsors.size();
		
		Event event = new Event();
		event.setName(name1);
		event.setAbout(about1);
		event.setCharity(charity1);
		sponsorManager.addEvent(event);
		
		List<Event> eventsTab = new ArrayList<Event>();
		eventsTab.add(event);
		
		Sponsor sponsor = new Sponsor();
		sponsor.setName(nameU1);
		sponsor.setAbout(about1);
		sponsor.setEvents(eventsTab);
		sponsorManager.addSponsor(sponsor);
		
		assertNotNull(sponsorManager.findSponsorById(sponsor.getId()));
		assertNotNull(sponsorManager.findEventById(event.getId()));
		
		sponsorManager.deleteSponsor(sponsor);
		assertNull(sponsorManager.findEventById(event.getId()));
		assertNotNull(sponsorManager.findSponsorById(sponsor.getId()));
		
		List<Sponsor> sponsors2 = sponsorManager.getAllSponsors();
		int sponsorCount2 = sponsors2.size();
		
		assertEquals(sponsorsCount, sponsorCount2);
	}
	
	@Test
	public void findAllSponsorsCheck() {
	
		sponsorManager.addSponsors();
	
		Event event1 = new Event();
		event1.setName(name2);
		event1.setAbout(about2);
		event1.setCharity(charity2);
	
		Event event2 = new Event();
		event2.setName(name2);
		event2.setAbout(about2);
		event2.setCharity(charity2);
	
		sponsorManager.addEvent(event1);
		sponsorManager.addEvent(event2);
	
		List<Event> eventsTab = new ArrayList<Event>();
		eventsTab.add(event1);
		eventsTab.add(event2);
	
		Sponsor sponsor = new Sponsor();
		sponsor.setName(nameU2);
		sponsor.setAbout(about2);
		sponsor.setEvents(eventsTab);
	
	
		List<Sponsor> sponsors = sponsorManager.getAllSponsors();
		int sponsorsCount = sponsors.size();
	
		sponsorManager.addSponsor(sponsor);
	
		List<Sponsor> sponsors2 = sponsorManager.getAllSponsors();
		int sponsorsCount2 = sponsors2.size();
	
		assertNotNull(sponsors);
	
		assertEquals(sponsorsCount2, sponsorsCount+1);
	}
	
	
	@Test
	public void editSponsorCheck() {
		
		sponsorManager.addSponsors();
		List<Sponsor> sponsors1 = sponsorManager.getAllSponsors();
		int sponsorsCount = sponsors1.size();
		
		Event event1 = new Event();
		event1.setName(name2);
		event1.setAbout(about2);
		event1.setCharity(charity2);
		
		Event event2 = new Event();
		event2.setName(name2);
		event2.setAbout(about2);
		event2.setCharity(charity2);
				
		sponsorManager.addEvent(event1);
		sponsorManager.addEvent(event2);
		
		List<Event> eventsTab1 = new ArrayList<Event>();
		eventsTab1.add(event1);
		
		Sponsor sponsor1 = new Sponsor();
		sponsor1.setName(nameU1);
		sponsor1.setAbout(about1);
		sponsor1.setEvents(eventsTab1);
		sponsorManager.addSponsor(sponsor1);
		
		List<Event> eventsTab2 = new ArrayList<Event>();
		eventsTab2.add(event2);
		
		Sponsor sponsor2 = new Sponsor();
		sponsor2.setName(nameU2);
		sponsor2.setAbout(about2);
		sponsor2.setEvents(eventsTab2);
		sponsorManager.addSponsor(sponsor2);

		
		List<Sponsor> sponsors2 = sponsorManager.getAllSponsors();
		int sponsorsCount2 = sponsors2.size();
		assertEquals(sponsorsCount+2, sponsorsCount2);
		
		
		sponsor2.setName(name1);
		sponsor2.setAbout(about1);
		
		sponsorManager.editSponsor(sponsor2);
		
		Long idSponsor = sponsor2.getId();
		
		Sponsor sp = sponsorManager.findSponsorById(idSponsor);
		
		assertEquals(name1, sp.getName());
		assertEquals(about1, sp.getAbout());

	}
	
	
	@Test
	public void findSponsorByBranch() {
		
		sponsorManager.addSponsors();
		List<Sponsor> sponsors1 = sponsorManager.getAllSponsors();
		int sponsorsCount = sponsors1.size();
		
		Event event = new Event();
		event.setName(name1);
		event.setAbout(aboutUnique);
		event.setCharity(charity1);		
		sponsorManager.addEvent(event);
		
		List<Event> eventsTab1 = new ArrayList<Event>();
		eventsTab1.add(event);
		
		Sponsor sponsor1 = new Sponsor();
		sponsor1.setName(nameU1);
		sponsor1.setAbout(aboutUnique);
		sponsor1.setEvents(eventsTab1);
		sponsorManager.addSponsor(sponsor1);
		
		
		assertNotNull(sponsorManager.findEventById(event.getId()));
		assertNotNull(sponsorManager.findSponsorById(sponsor1.getId()));
		
		List<Sponsor> sponsors2 = sponsorManager.getAllSponsors();
		int sponsorsCount2 = sponsors2.size();
		
		assertEquals(sponsorsCount2, sponsorsCount+1);
		
		int found = 0;
		assertNotNull(sponsorManager.findSponsorByBranch(sponsor1.getBranch()));
		List<Sponsor> spList = sponsorManager.findSponsorByBranch(sponsor1.getBranch());
		for(Sponsor sp : spList)
		{
			if(sp.getId() == sponsor1.getId())
			{
				assertEquals(sp.getBranch(),sponsor1.getBranch());
				found++;
			}
		}
		assertEquals(found,1);
	}
	
	//test pobrania obiektow(x) nalezacych do y 
	@Test
	public void checkPobranieWszystkichObiektowZSponsoru() {
		Event event1 = new Event();
		event1.setName(name1);
		event1.setAbout(about1);
		event1.setCharity(charity1);
		
		Event event2 = new Event();
		event2.setName(name2);
		event2.setAbout(about2);
		event2.setCharity(charity2);
				
		sponsorManager.addEvent(event1);
		sponsorManager.addEvent(event2);
		
		List<Event> eventsTab1 = new ArrayList<Event>();
		eventsTab1.add(event1);
		eventsTab1.add(event2);
		
		Sponsor sponsor1 = new Sponsor();
		sponsor1.setName(nameU1);
		sponsor1.setAbout(about1);
		sponsor1.setEvents(eventsTab1);
		sponsorManager.addSponsor(sponsor1);
		
		List<Event> eventsOfSponsor = sponsorManager.getOwnedEvents(sponsor1);
		assertNotNull(eventsOfSponsor);
		
		assertEquals(eventsOfSponsor.size(), 2);
		
		Event ev1 = eventsOfSponsor.get(0);
		Event ev2 = eventsOfSponsor.get(1);
		
		assertEquals(ev1.getName(), name1);
		assertEquals(ev1.getAbout(), about1);
		assertEquals(ev1.getCharity(), charity1);
		
		assertEquals(ev2.getName(), name2);
		assertEquals(ev2.getAbout(), about2);
		assertEquals(ev2.getCharity(), charity2);
		
	}
	*/
}