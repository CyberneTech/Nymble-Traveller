package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.entity.ActivityBooking;
import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.repository.PassengerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service class with business logic for passenger details
 * 
 * @author Shruti
 * @version 1.0
 */

@Service
public class PassengerDetailsService {
    @Autowired
    private PassengerRepository passengerRepository;

    //service to create a new passenger entry
    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    // service to add balance to wallet of a user
    @Transactional
    public Passenger rechargeWallet(Long passengerNumber, int amount) throws Exception {
        // to maintain ACID properties of transaction
        Passenger passenger = passengerRepository.findById(passengerNumber)
                .orElseThrow(() -> new Exception("Passenger not found"));
        passenger.setWalletBalance(passenger.getWalletBalance() + amount);
        return passengerRepository.save(passenger);
    }

    // To fetch and return passenger details
    public Passenger getPassengerDetails(Long passengerNumber) {
        return passengerRepository.findById(passengerNumber).orElse(null);
    }

    public List<Passenger> getAllPassengerDetails() {
        return passengerRepository.findAll();
    }

    /**
     * @param passengerNumber
     * @return List of activities booked by the passenger, and passenger
     *         details(name, passengerNumber, balance)
     * @throws EntityNotFoundException
     * 
     */

    public Map<String, Object> getPassengerActivityBookingDetails(Long passengerNumber) {
        Passenger passenger = passengerRepository.findById(passengerNumber)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found"));

        Map<String, Object> passengerDetails = new LinkedHashMap<>();
        passengerDetails.put("name", passenger.getPassengerName());
        passengerDetails.put("passengerNumber", passenger.getPassengerNumber());
        passengerDetails.put("balance", passenger.getWalletBalance());

        List<Map<String, Object>> activitiesList = getPassengerActivities(passenger);

        passengerDetails.put("activities", activitiesList);
        return passengerDetails;
    }

    private static List<Map<String, Object>> getPassengerActivities(Passenger passenger) {
        List<Map<String, Object>> activitiesList = new ArrayList<>();
        for (ActivityBooking booking : passenger.getActivityBookings()) {
            Map<String, Object> activityDetails = new HashMap<>();
            Activity activity = booking.getActivity();

            activityDetails.put("activityName", activity.getActivityName());
            activityDetails.put("description", activity.getDescription());
            activityDetails.put("cost", activity.getCost());
            activityDetails.put("destination", activity.getDestination().getDestinationName());
            activityDetails.put("amountPaid", booking.getAmountPaid());

            activitiesList.add(activityDetails);
        }
        return activitiesList;
    }

}
