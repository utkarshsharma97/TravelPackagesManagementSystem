package com.abhinav.travelpackages.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoldPassenger extends Passenger{

	public GoldPassenger(String passengerName, int passengerNumber, double balanceAmount, Map<Destination, List<Activity>> signedUpActivties) {
		super(passengerName, passengerNumber, balanceAmount, signedUpActivties);
	}
	
	@Override
	public boolean signUpForAnActivity(Destination destination, Activity activity) {

		//validate if the Gold passenger can possibly sign up for the given activity
		if(!validatePassengerSignUpForActivty(activity)) {
			return false;
		}
		else {
			double passengerBalance = this.balanceAmount;
			double discountedActivityCost = activity.getCost() * 0.90;
			int activityCapacity = activity.getCapacity();
			Map<Destination, List<Activity>> signedUpActivties = this.getSignedUpActivties();
			
			
			//update balance for the passenger
			this.balanceAmount =  passengerBalance - discountedActivityCost;
			
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
		if((activity.getCapacity() == 0) || (this.balanceAmount - activity.getCost() * 0.90 < 0.0)) {
			return false;
		}
		return true;
	}
}
