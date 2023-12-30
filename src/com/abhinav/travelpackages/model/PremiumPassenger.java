package com.abhinav.travelpackages.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PremiumPassenger extends Passenger{

	public PremiumPassenger(String passengerName, int passengerNumber, double balanceAmount, Map<Destination, List<Activity>> signedUpActivties) {
		super(passengerName, passengerNumber, balanceAmount, signedUpActivties);
	}
	
	@Override
	public void printPassengerDetails() {
		System.out.println("Passenger Name : " + this.passengerName);
		System.out.println("Passenger Number : " + this.passengerNumber);
		
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
	}
	
	@Override
	public boolean signUpForAnActivity(Destination destination, Activity activity) {

		//validate if the Premium passenger can possibly sign up for the given activity
		if(!validatePassengerSignUpForActivty(activity)) {
			return false;
		}
		else {
			int activityCapacity = activity.getCapacity();
			Map<Destination, List<Activity>> signedUpActivties = this.getSignedUpActivties();
			
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
	
	@Override
	public boolean validatePassengerSignUpForActivty(Activity activity) {
		if((activity.getCapacity() == 0)) {
			return false;
		}
		return true;
	}
}
