package com.myapp.waste.mystory.Service;

import java.sql.Blob;



public class Profile {
private String username;
private String first_name;
private String last_name;
private String city;
private String country;
private int pincode;
private String mobile;
private String interest;
private int score;
private String profilepicpath;

public Profile(String username, String first_name, String last_name,
		String city, String country, int pincode, String mobile,
		String interest, int score,String profilepicpath) {
	super();
	this.username = username;
	this.first_name = first_name;
	this.last_name = last_name;
	this.city = city;
	this.country = country;
	this.pincode = pincode;
	this.mobile = mobile;
	this.interest = interest;
	this.score = score;
	this.profilepicpath = profilepicpath;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getLast_name() {
	return last_name;
}

public void setLast_name(String last_name) {
	this.last_name = last_name;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public int getPincode() {
	return pincode;
}

public void setPincode(int pincode) {
	this.pincode = pincode;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getInterest() {
	return interest;
}

public void setInterest(String interest) {
	this.interest = interest;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

public String getProfilepicpath() {
	return profilepicpath;
}

public void setProfilepicpath(String profilepicpath) {
	this.profilepicpath = profilepicpath;
}


}
