package demo.namegame.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("mimeType")
	private String mimeType;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("height")
	private double height;
	
	@JsonProperty("width")
	private double width;
	
	@JsonProperty("alt")
	private String alt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
}
