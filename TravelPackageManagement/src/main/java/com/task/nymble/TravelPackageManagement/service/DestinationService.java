package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Destination;
import com.task.nymble.TravelPackageManagement.repository.DestinationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class with business logic for Destination entity
 * addDestination(), getAllDestinations() methods for creating, getting
 * destinations
 * 
 */

@Service
public class DestinationService {

    @Autowired
    private DestinationRespository destinationRespository;

    // Service method to add a new destination
    public Destination addDestination(Destination destination) {
        return destinationRespository.save(destination);
    }

    // Service method to get all destinations
    public List<Destination> getAllDestinations() {
        return destinationRespository.findAll();
    }
}
