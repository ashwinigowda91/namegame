package demo.namegame.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.namegame.app.domain.Person;
import demo.namegame.app.service.GetProfilesService;

@Controller
public class AppController {

	@Autowired
	private GetProfilesService getProfilesService;

	@RequestMapping(value = "/list-profiles", method = RequestMethod.GET)
	public String getHomePage() {
		return "list-profiles";
	}

	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> getProfiles(HttpSession session) {
		List<Person> personList = getProfilesService.getRandomPersons();
		session.setAttribute("profiles", personList);
		return personList;
	}
	
	@RequestMapping(value = "/select-person-name", method = RequestMethod.GET)
	@ResponseBody
	public String getPersonName(HttpSession session) {
		List<Person> personList = (List<Person>) session.getAttribute("profiles");
		Person person = getProfilesService.selectPerson(personList);
		String name = null;
		if (person != null) {
			name = person.getFirstName() + " " + person.getLastName();
			session.setAttribute("id", person.getId());
		} else {
			name = "";
		}
		return name;
	}

	@RequestMapping(value = "/check-response/{personId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkResponseFromUser(@PathVariable("personId") String id, HttpSession session) {
		return getProfilesService.checkResponse(String.valueOf(session.getAttribute("id")), id);
	}
	
	@RequestMapping(value = "/select-person", method = RequestMethod.GET)
	@ResponseBody
	public Person getSelectPerson(HttpSession session) {
		List<Person> personList = (List<Person>) session.getAttribute("profiles");
		Person person = getProfilesService.selectPerson(personList);
		session.setAttribute("id", person.getId());
		return person;
	}
}
