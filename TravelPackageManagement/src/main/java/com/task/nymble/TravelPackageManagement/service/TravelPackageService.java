package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.entity.Destination;
import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.entity.TravelPackage;
import com.task.nymble.TravelPackageManagement.repository.PassengerRepository;
import com.task.nymble.TravelPackageManagement.repository.TravelPackageRepository;
import com.task.nymble.TravelPackageManagement.repository.DestinationRespository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;


/**
 * Service class with business logic for travel package details
 * saveTravelPackage(), updateTravelPackage(), getTravelPackage(),
 * getAllTravelPackages() for CRUD operations
 */

@Service
public class TravelPackageService {

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private DestinationRespository destinationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public TravelPackage saveTravelPackage(TravelPackage travelPackage) {
        return travelPackageRepository.save(travelPackage);
    }

    public TravelPackage updateTravelPackage(Long travelPackageId, TravelPackage travelPackage) throws Exception {
        if (!travelPackageRepository.existsById(travelPackageId)) {
            throw new Exception("TravelPackage not found");
        }
        return travelPackageRepository.save(travelPackage);
    }

    public TravelPackage getTravelPackage(Long travelPackageId) {
        return travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new RuntimeException("TravelPackage not found"));
    }

    public List<TravelPackage> getAllTravelPackages() {
        List<TravelPackage> packages = travelPackageRepository.findAll();
        return packages;
    }

    /**
     * Method to get the travel package itinerary, includes package_name, list of
     * destinations, and list of activities
     * 
     * @param packageId
     * @return Map<String, Object> with travel package details
     */

    public Map<String, Object> getTravelPackageItinerary(Long packageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(packageId)
                .orElseThrow(() -> new EntityNotFoundException("TravelPackage not found"));

        Map<String, Object> itineraryMap = new LinkedHashMap<>();
        itineraryMap.put("packageName", travelPackage.getTravelPackageName());
        List<Map<String, String>> destinationDetails = new ArrayList<>();

        for (Destination destination : travelPackage.getDestinations()) {
            Map<String, String> destinationMap = new HashMap<>();
            destinationMap.put("destinationName", destination.getDestinationName());
            List<String> activitiesList = new ArrayList<>();

            for (Activity activity : destination.getActivities()) {
                String activityDetail = "Activity: " + activity.getActivityName() +
                        ", Cost: " + activity.getCost() +
                        ", Capacity: " + activity.getCapacity() +
                        ", Description: " + activity.getDescription();
                activitiesList.add(activityDetail);
            }

            destinationMap.put("activities", String.join("; ", activitiesList));
            destinationDetails.add(destinationMap);
        }

        itineraryMap.put("destinations", destinationDetails);
        return itineraryMap;
    }

    /**
     * Method to get the travel package passenger list, includes package_name, list
     * of passengers enrolled
     * 
     * @param packageId
     * @return Map<String, Object> with travel package passenger list details
     */

    public Map<String, Object> getTravelPackagePassengerList(Long packageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(packageId)
                .orElseThrow(() -> new EntityNotFoundException("TravelPackage not found"));

        Map<String, Object> passengerListMap = new LinkedHashMap<>();
        passengerListMap.put("packageName", travelPackage.getTravelPackageName());
        passengerListMap.put("passengerCapacity", travelPackage.getPassengerCapacity());
        passengerListMap.put("currentEnrollments", travelPackage.getTotalEnrollments());

        List<Map<String, String>> passengers = new ArrayList<>();
        for (Passenger passenger : travelPackage.getPassengers()) {
            Map<String, String> passengerDetails = new HashMap<>();
            passengerDetails.put("name", passenger.getPassengerName());
            passengerDetails.put("number", passenger.getPassengerNumber().toString());
            passengers.add(passengerDetails);
        }

        passengerListMap.put("passengers", passengers);
        return passengerListMap;
    }

    /**
     * Method to book a travel package,
     * 
     * @param travelPackageId
     * @param passengerNumber
     * @return String with booking status
     * 
     */

    @Transactional
    public String bookTravelPackageService(Long travelPackageId, Long passengerNumber){
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new RuntimeException("TravelPackage not found"));
        Passenger passenger = passengerRepository.findById(passengerNumber)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        travelPackage.increaseCurrentCapacity();
        passenger.getTravelPackages().add(travelPackage);
        passengerRepository.save(passenger);
        return "Package booking successful";
    }

}
