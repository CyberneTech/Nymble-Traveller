package com.task.nymble.TravelPackageManagement.entity;

import com.task.nymble.TravelPackageManagement.entity.Destination;
import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.entity.TravelPackage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TravelPackageTests {

    @Test
    public void testCreateTravelPackage() {

        String travelPackageName = "Adventure Package";
        int passengerCapacity = 10;

        TravelPackage travelPackage = new TravelPackage(travelPackageName, passengerCapacity);

        // Assert
        assertNotNull(travelPackage);
        assertEquals(travelPackageName, travelPackage.getTravelPackageName());
        assertEquals(passengerCapacity, travelPackage.getPassengerCapacity());
        assertNotNull(travelPackage.getPassengers());
        assertNotNull(travelPackage.getDestinations());
    }

    @Test
    public void testIncreaseCurrentCapacity() {

        TravelPackage travelPackage = new TravelPackage("City Tour", 5);

        for (int i = 0; i < 5; i++) {
            travelPackage.increaseCurrentCapacity();
            assertEquals(i + 1, travelPackage.getTotalEnrollments());
        }

        // Try to exceed capacity
        assertThrows(IllegalStateException.class, travelPackage::increaseCurrentCapacity);
    }

    @Test
    public void testAddPassenger() {

        TravelPackage travelPackage = new TravelPackage("Nature Retreat", 3);
        Passenger passenger1 = new Passenger("Alice");
        Passenger passenger2 = new Passenger("Bob");

        travelPackage.getPassengers().add(passenger1);
        travelPackage.getPassengers().add(passenger2);

        // Assert
        assertThat(travelPackage.getPassengers()).containsExactlyInAnyOrder(passenger1, passenger2);
    }

    @Test
    public void testAddDestination() {

        TravelPackage travelPackage = new TravelPackage("Mountain Hike", 8);
        Destination destination1 = new Destination("Mountain View");
        Destination destination2 = new Destination("Lake Shore");

        travelPackage.getDestinations().add(destination1);
        travelPackage.getDestinations().add(destination2);

        // Assert
        assertThat(travelPackage.getDestinations()).containsExactlyInAnyOrder(destination1, destination2);
    }
}

