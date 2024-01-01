package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivity(String activityName, Activity activity) throws Exception {
        if (!activityRepository.existsById(activityName)) {
            throw new Exception("Activity not found");
        }
        return activityRepository.save(activity);
    }

    public Activity getActivity(String activityName) {
        return activityRepository.findById(activityName)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    public List<Activity> getAllAvailableActivities() {
        return activityRepository.findByCapacityGreaterThan(0);
    }
}
