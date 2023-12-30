package com.abhinav.travelpackages.model;

import java.util.List;

public class TravelPackage {
	
	private String name;
	private int capacity;
	private List<Destination> destinations;
	private List<Passenger> passengers;
	
	public TravelPackage(String name, int capacity, List<Destination> destinations, List<Passenger> passengers) {
		if(passengers.size() > capacity) {
			return;
		}
		this.name = name;
		this.capacity = capacity - passengers.size();
		this.destinations = destinations;
		this.passengers = passengers;
	}
	
	public void printItinerary() {
		System.out.println(">>> TRAVEL ITINERARY FOR PACKAGE <<<");
		System.out.println("Travel package name : " + this.name + "\n");
		List<Destination> destinations = this.destinations;
		for(Destination destination : destinations) {
			System.out.println("Destination name : " + destination.getName());
			System.out.println("Activities : ");
			List<Activity> activities = destination.getActivties();
			for(Activity activity : activities) {
				System.out.println("Activity Name : " + activity.getName());
				System.out.println("Activity Cost : " + activity.getCost());
				System.out.println("Activity Capacity : " + activity.getCapacity());
				System.out.println("Activity Description : " + activity.getDescription() + "\n");
			}
		}
		
	}
	
	public void printPassengerDetailsForPackage() {
		System.out.println(">>> PASSENGER DETAILS <<<");
		System.out.println("Travel package name : " + this.name);
		System.out.println("Travel package capacity : " + this.capacity + "\n");
		System.out.println("Number of passengers enrolled : " + this.passengers.size());
		
		List<Passenger> passengers = this.passengers;
		for(Passenger passenger : passengers) {
			System.out.println("Passenger name : " + passenger.passengerName);
			System.out.println("Passenger number : " + passenger.passengerNumber);
		}
	}
}
