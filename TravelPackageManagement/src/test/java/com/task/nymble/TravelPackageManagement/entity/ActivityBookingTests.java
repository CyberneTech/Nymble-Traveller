package com.task.nymble.TravelPackageManagement.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityBookingTests {

    @Test
    public void testCreateActivityBooking() {

        int amountPaid = 50;
        ActivityBooking activityBooking = new ActivityBooking(amountPaid);

        // Assert
        assertNotNull(activityBooking);
        assertEquals(amountPaid, activityBooking.getAmountPaid());
    }

    @Test
    public void testSetPassenger() {

        ActivityBooking activityBooking = new ActivityBooking(60);
        Passenger passenger = new Passenger("Alice");
        activityBooking.setPassenger(passenger);

        // Assert
        assertThat(activityBooking.getPassenger()).isEqualTo(passenger);
    }

    @Test
    public void testSetActivity() {

        ActivityBooking activityBooking = new ActivityBooking(70);
        Activity activity = new Activity("Hiking", "Enjoy a scenic hike", 50, 20, null);
        activityBooking.setActivity(activity);

        // Assert
        assertThat(activityBooking.getActivity()).isEqualTo(activity);
    }

}

