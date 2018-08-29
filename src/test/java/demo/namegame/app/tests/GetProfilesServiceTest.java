package demo.namegame.app.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import demo.namegame.app.domain.Person;
import demo.namegame.app.service.GetProfilesService;

public class GetProfilesServiceTest {
	
	@Test
	public void testGetProfiles() {
		GetProfilesService profileService = new GetProfilesService();
		List<Person> list = profileService.getProfiles();
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testGetRandomPersons() {
		GetProfilesService profileService = new GetProfilesService();
		List<Person> list = profileService.getRandomPersons();
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 6);
	}
	
	@Test
	public void testSelectPerson() {
		GetProfilesService profileService = new GetProfilesService();
		List<Person> list = profileService.getRandomPersons();
		assertNotNull(list);
		assertFalse(list.isEmpty());
		Person person = profileService.selectPerson(list);
		assertNotNull(person);
	}
}
