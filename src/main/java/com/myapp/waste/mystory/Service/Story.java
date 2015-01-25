package com.myapp.waste.mystory.Service;

public class Story {
	private int id;
	private String heading;
	private String location;
	private String username;
	private String picpath;
	private String story;
	private String type;
	private int like;
	
	public Story(String heading, String location, String username,
			String picpath, String story, String type, int like) {
		super();
		this.heading = heading;
		this.location = location;
		this.username = username;
		this.picpath = picpath;
		this.story = story;
		this.type = type;
		this.like = like;
	}
	public Story(int id,String heading, String location, String username,
			String picpath, String story, String type, int like) {
		super();
		this.id = id;
		this.heading = heading;
		this.location = location;
		this.username = username;
		this.picpath = picpath;
		this.story = story;
		this.type = type;
		this.like = like;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
}