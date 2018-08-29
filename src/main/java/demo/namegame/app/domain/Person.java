package demo.namegame.app.domain;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("jobTitle")
	private String jobTitle;
	
	@JsonProperty("socialLinks")
	private ArrayList<SocialLink> socialLinks;
	
	@JsonProperty("headshot")
	private Image image;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public ArrayList<SocialLink> getSocialLinks() {
		return socialLinks;
	}

	public void setSocialLinks(ArrayList<SocialLink> socialLinks) {
		this.socialLinks = socialLinks;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
