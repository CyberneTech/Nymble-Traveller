package com.task.nymble.TravelPackageManagement.entity;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.entity.Destination;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityTests {

    @Test
    public void testCreateActivity() {

        String activityName = "Hiking";
        String description = "Enjoy a scenic hike";
        int cost = 50;
        int capacity = 20;
        Destination destination = new Destination("Mountain View");

        Activity activity = new Activity(activityName, description, cost, capacity, destination);

        // Assert
        assertNotNull(activity);
        assertEquals(activityName, activity.getActivityName());
        assertEquals(description, activity.getDescription());
        assertEquals(cost, activity.getCost());
        assertEquals(capacity, activity.getCapacity());
        assertEquals(destination, activity.getDestination());
    }

    @Test
    public void testActivityToString() {

        Activity activity = new Activity();
        activity.setActivityName("Sightseeing");
        activity.setDescription("Explore the city");
        activity.setCost(30);
        activity.setCapacity(25);

        String activityString = activity.toString();

        // Assert
        assertTrue(activityString.contains("Sightseeing"));
        assertTrue(activityString.contains("Explore the city"));
        assertTrue(activityString.contains("30"));
        assertTrue(activityString.contains("25"));
    }

}
