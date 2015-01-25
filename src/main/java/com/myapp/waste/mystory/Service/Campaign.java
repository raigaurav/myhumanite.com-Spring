package com.myapp.waste.mystory.Service;

public class Campaign {
private int idcampaign;
private String username;
private String heading;
private String purpose;
private String category;
private String location;
private String city;
private String date;

public Campaign(String username, String heading, String purpose,
		String category, String location, String city, String date) {
	super();
	this.username = username;
	this.heading = heading;
	this.purpose = purpose;
	this.category = category;
	this.location = location;
	this.city = city;
	this.date = date;
}

public Campaign(int idcampaign,String username, String heading, String purpose,
		String category, String location, String city, String date) {
	super();
	this.idcampaign = idcampaign;
	this.username = username;
	this.heading = heading;
	this.purpose = purpose;
	this.category = category;
	this.location = location;
	this.city = city;
	this.date = date;
}

public int getIdcampaign() {
	return idcampaign;
}

public void setIdcampaign(int idcampaign) {
	this.idcampaign = idcampaign;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getHeading() {
	return heading;
}

public void setHeading(String heading) {
	this.heading = heading;
}

public String getPurpose() {
	return purpose;
}

public void setPurpose(String purpose) {
	this.purpose = purpose;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

}