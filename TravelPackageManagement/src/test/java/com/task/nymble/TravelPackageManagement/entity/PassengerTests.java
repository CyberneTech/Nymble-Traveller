package com.task.nymble.TravelPackageManagement.entity;

import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.service.Constants.MembershipType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerTests {

    @Test
    public void testCreatePassenger() {

        String passengerName = "John Doe";
        int walletBalance = 100;
        MembershipType membershipType = MembershipType.GOLD;

        Passenger passenger = new Passenger(passengerName, walletBalance, membershipType);

        // Assert
        assertNotNull(passenger);
        assertEquals(passengerName, passenger.getPassengerName());
        assertEquals(walletBalance, passenger.getWalletBalance());
        assertEquals(membershipType, passenger.getMembershipType());
    }

    @Test
    public void testCreatePassengerWithDefaults() {

        String passengerName = "Guest";

        Passenger passenger = new Passenger(passengerName);

        // Assert
        assertNotNull(passenger);
        assertEquals(passengerName, passenger.getPassengerName());
        assertEquals(0, passenger.getWalletBalance());
        assertEquals(MembershipType.STANDARD, passenger.getMembershipType());
    }

}
