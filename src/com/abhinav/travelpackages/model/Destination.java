package com.abhinav.travelpackages.model;

import java.util.List;

public class Destination {
	
	private String name;
	private List<Activity> activties;
	
	public Destination(String name, List<Activity> activties) {
		this.name = name;
		this.activties = activties;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Activity> getActivties(){
		return this.activties;
	}
}