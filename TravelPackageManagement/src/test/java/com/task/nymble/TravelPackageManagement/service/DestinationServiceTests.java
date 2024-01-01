package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Destination;
import com.task.nymble.TravelPackageManagement.repository.DestinationRespository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DestinationServiceTests {

    @Mock
    private DestinationRespository destinationRepository;

    @InjectMocks
    private DestinationService destinationService;

    @Test
    void addDestination() {
        Destination destination = new Destination("Mountain View");

        when(destinationRepository.save(destination)).thenReturn(destination);
        Destination addedDestination = destinationService.addDestination(destination);

        assertNotNull(addedDestination);
        assertEquals(destination.getDestinationName(), addedDestination.getDestinationName());
    }

    @Test
    void getAllDestinations() {
        List<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination("Mountain View"));
        destinations.add(new Destination("Beach Resort"));

        when(destinationRepository.findAll()).thenReturn(destinations);
        List<Destination> resultDestinations = destinationService.getAllDestinations();

        assertNotNull(resultDestinations);
        assertEquals(destinations.size(), resultDestinations.size());
    }

    @Test
    void addDestinationWithActivities() {
        Destination destination = new Destination("Mountain View");
        destination.setActivities(new ArrayList<>()); // Set activities as needed

        when(destinationRepository.save(destination)).thenReturn(destination);
        Destination addedDestination = destinationService.addDestination(destination);

        assertNotNull(addedDestination);
        assertEquals(destination.getDestinationName(), addedDestination.getDestinationName());
        assertNotNull(addedDestination.getActivities());
    }

    @Test
    void addDestinationWithTravelPackages() {
        Destination destination = new Destination("Mountain View");
        destination.setTravelPackages(new ArrayList<>()); // Set travel packages as needed

        when(destinationRepository.save(destination)).thenReturn(destination);
        Destination addedDestination = destinationService.addDestination(destination);

        assertNotNull(addedDestination);
        assertEquals(destination.getDestinationName(), addedDestination.getDestinationName());
        assertNotNull(addedDestination.getTravelPackages());
    }
}
