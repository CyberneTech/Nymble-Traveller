package com.task.nymble.TravelPackageManagement.entity;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.entity.Destination;
import com.task.nymble.TravelPackageManagement.entity.TravelPackage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DestinationTests {

    @Test
    public void testCreateDestination() {

        String destinationName = "Beach Resort";

        Destination destination = new Destination(destinationName);

        // Assert
        assertNotNull(destination);
        assertEquals(destinationName, destination.getDestinationName());
        assertNotNull(destination.getActivities());
        assertNotNull(destination.getTravelPackages());
    }

    @Test
    public void testDestinationToString() {

        Destination destination = new Destination();
        destination.setDestinationId(1L);
        destination.setDestinationName("Mountain View");

        List<Activity> activities = new ArrayList<>();
        Activity activity1 = new Activity("Hiking", "Enjoy a scenic hike", 50, 20, destination);
        Activity activity2 = new Activity("Camping", "Camp under the stars", 80, 15, destination);
        activities.add(activity1);
        activities.add(activity2);
        destination.setActivities(activities);

        String destinationString = destination.toString();

        // Assert
        assertThat(destinationString).contains("Mountain View");
        assertThat(destinationString).contains("Hiking");
        assertThat(destinationString).contains("Camping");
    }

}

