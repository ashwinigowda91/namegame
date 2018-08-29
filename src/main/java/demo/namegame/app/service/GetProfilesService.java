package demo.namegame.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.namegame.app.domain.Person;

@Service
public class GetProfilesService {

	public List<Person> getProfiles() {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String url = "https://www.willowtreeapps.com/api/v1.0/profiles";
		List<Person> personList = new ArrayList<Person>();
		try {
			ResponseEntity<List<Person>> result = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<Person>>() {
					});
			int statusCode = result.getStatusCodeValue();
			if (statusCode == 200) {
				personList = result.getBody();
			} else {
				System.out.println("Error in getting profiles from API");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personList;
	}

	public List<Person> getRandomPersons() {
		List<Person> personList = getProfiles();
		List<Person> result = new ArrayList<Person>();
		Set<Integer> indexSet = new HashSet<Integer>();
		if (personList != null && !personList.isEmpty()) {
			Collections.shuffle(personList);
			Random random = new Random();
			int i=0;
			while(i < 6) {
				int index = random.nextInt(personList.size());
				while (!indexSet.contains(index)) {
					indexSet.add(index);
					result.add(personList.get(index));
					i++;
				}
			}
		}
		return result;
	}
	
	public Person selectPerson(List<Person> personList) {
		Person person = null;
		if(personList != null && !personList.isEmpty()) {
			Random rand = new Random();
			int index = rand.nextInt(personList.size());
			person = personList.get(index);
		}
		return person;
	}
	
	public boolean checkResponse(String sessionId, String userInput) {
		boolean match = false;
		if(sessionId != null && !sessionId.isEmpty()) {
			if(sessionId.equals(userInput)) {
				match = true;
			}
		} 
		return match;
	}
}
