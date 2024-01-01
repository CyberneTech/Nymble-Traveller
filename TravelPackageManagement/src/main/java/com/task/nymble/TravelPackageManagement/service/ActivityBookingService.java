package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.entity.ActivityBooking;
import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.repository.ActivityBookingRepository;
import com.task.nymble.TravelPackageManagement.repository.PassengerRepository;
import com.task.nymble.TravelPackageManagement.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ActivityBookingService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityBookingRepository activityBookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public List<ActivityBooking>  getAllActivityBookings() {
        return activityBookingRepository.findAll();
    }

    @Transactional
    public void bookActivity(String activityName, Long passengerNumber){
        Passenger passenger = passengerRepository.findById(passengerNumber)
                .orElseThrow(() -> new IllegalStateException("Passenger not found"));


        Activity activity = activityRepository.findById(activityName)
                .orElseThrow(() -> new IllegalStateException("Activity not found"));

        if(activity.getCapacity() == 0) {
            throw new IllegalStateException("Capacity Reached");
        }
        else {
                double cost = passenger.getMembershipType().getPrice(activity.getCost());
                double newWalletBalance = (passenger.getWalletBalance() - cost);
                if(newWalletBalance<0) {
                    throw new IllegalStateException("Insufficient Balance");
                }
                // Update wallet balance
                passenger.setWalletBalance(newWalletBalance);
                // Reduce activity capacity
                activity.setCapacity(activity.getCapacity() - 1);

                // Make new activity booking
                ActivityBooking activityBooking = new ActivityBooking(cost);
                activityBooking.setPassenger(passenger);
                activityBooking.setActivity(activity);

                // Set all updated entities
                passengerRepository.save(passenger);
                activityRepository.save(activity);
                activityBookingRepository.save(activityBooking);
        }
    }
}
