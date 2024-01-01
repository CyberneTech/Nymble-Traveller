package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ActivityServiceTests {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

    @Test
    void saveActivity() {
        Activity activity = new Activity("Hiking", "Outdoor adventure", 50, 10, null);

        when(activityRepository.save(activity)).thenReturn(activity);
        Activity savedActivity = activityService.saveActivity(activity);

        assertNotNull(savedActivity);
        assertEquals(activity.getActivityName(), savedActivity.getActivityName());
    }

    @Test
    void updateExistingActivity() throws Exception {
        Activity existingActivity = new Activity("Hiking", "Outdoor adventure", 50, 10, null);
        Activity updatedActivity = new Activity("Hiking", "New Description", 60, 15, null);

        when(activityRepository.existsById(existingActivity.getActivityName())).thenReturn(true);
        when(activityRepository.save(updatedActivity)).thenReturn(updatedActivity);
        Activity resultActivity = activityService.updateActivity(existingActivity.getActivityName(), updatedActivity);

        assertNotNull(resultActivity);
        assertEquals(updatedActivity.getActivityName(), resultActivity.getActivityName());
    }

    @Test
    void updateNonExistingActivity_shouldThrowException() {
        Activity updatedActivity = new Activity("NonExistingActivity", "New Description", 60, 15, null);

        when(activityRepository.existsById(updatedActivity.getActivityName())).thenReturn(false);

        assertThrows(Exception.class, () -> activityService.updateActivity(updatedActivity.getActivityName(), updatedActivity));
    }

    @Test
    void getActivity() {
        String activityName = "Hiking";
        Activity expectedActivity = new Activity(activityName, "Outdoor adventure", 50, 10, null);

        when(activityRepository.findById(activityName)).thenReturn(Optional.of(expectedActivity));
        Activity resultActivity = activityService.getActivity(activityName);

        assertNotNull(resultActivity);
        assertEquals(expectedActivity.getActivityName(), resultActivity.getActivityName());
    }

    @Test
    void getActivityForNonExistingActivity_shouldThrowException() {
        String nonExistingActivityName = "NonExistingActivity";

        when(activityRepository.findById(nonExistingActivityName)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> activityService.getActivity(nonExistingActivityName));
    }

    @Test
    void getAllAvailableActivities() {
        List<Activity> availableActivities = new ArrayList<>();
        availableActivities.add(new Activity("Hiking", "Outdoor adventure", 50, 10, null));
        availableActivities.add(new Activity("Sightseeing", "City exploration", 60, 15, null));

        when(activityRepository.findByCapacityGreaterThan(0)).thenReturn(availableActivities);
        List<Activity> resultActivities = activityService.getAllAvailableActivities();

        assertNotNull(resultActivities);
        assertEquals(availableActivities.size(), resultActivities.size());
    }
}

