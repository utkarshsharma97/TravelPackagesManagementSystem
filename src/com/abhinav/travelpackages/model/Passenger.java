package com.abhinav.travelpackages.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Passenger {
	
	protected String passengerName;
	protected int passengerNumber;
	protected double balanceAmount;
	protected Map<Destination, List<Activity>> signedUpActivties;
	
	
	public Passenger(String passengerName, int passengerNumber, double balanceAmount, Map<Destination, List<Activity>> signedUpActivties) {
		this.passengerName = passengerName;
		this.passengerNumber = passengerNumber;
		this.balanceAmount = balanceAmount;
		this.signedUpActivties = signedUpActivties;
	}
	
	public Map<Destination, List<Activity>> getSignedUpActivties() {
		return signedUpActivties;
	}

	public void setSignedUpActivties(Map<Destination, List<Activity>> signedUpActivties) {
		this.signedUpActivties = signedUpActivties;
	}
	
	public void printPassengerDetails() {
		System.out.println("Passenger Name : " + this.passengerName);
		System.out.println("Passenger Number : " + this.passengerNumber);
		System.out.println("Passenger Balance : " + this.balanceAmount);
		
		Map<Destination, List<Activity>> signedUpActivties = this.signedUpActivties;
		
		for(Destination destination : signedUpActivties.keySet()) {
			String destinationName = destination.getName();
			System.out.println("Destination Name : " + destinationName);
			List<Activity> activities = signedUpActivties.get(destination);
			for(Activity activity : activities) {
				System.out.print(activity.getName() + " ");
				System.out.println(activity.getCost());
			}
		}
		System.out.println();
	}

	public boolean signUpForAnActivity(Destination destination, Activity activity) {

		//validate if the passenger can possibly sign up for the given activity
		if(!validatePassengerSignUpForActivty(activity)) {
			return false;
		}
		else {
			double passengerBalance = this.balanceAmount;
			double activityCost = activity.getCost();
			int activityCapacity = activity.getCapacity();
			Map<Destination, List<Activity>> signedUpActivties = this.getSignedUpActivties();
			
			
			//update balance for the passenger
			this.balanceAmount =  passengerBalance - activityCost;
			
			//reduce capacity for the activity by 1
			activity.setCapacity(activityCapacity - 1);
			
			//add the activity to the list of passenger's activities
			if(signedUpActivties.get(destination) == null) {
				signedUpActivties.put(destination, new ArrayList<>());
			}
			signedUpActivties.get(destination).add(activity);
			this.setSignedUpActivties(signedUpActivties);
			
			return true;
		}
	}
	
	public boolean validatePassengerSignUpForActivty(Activity activity) {
		if((activity.getCapacity() == 0) || (this.balanceAmount - activity.getCost() < 0)) {
			return false;
		}
		return true;
	}
}