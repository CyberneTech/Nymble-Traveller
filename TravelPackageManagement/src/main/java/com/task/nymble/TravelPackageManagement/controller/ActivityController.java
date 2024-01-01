package com.task.nymble.TravelPackageManagement.controller;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // create new Activities
    @PostMapping("/new")
    public ResponseEntity<?> createActivity(@RequestBody Activity activity) {
        try {
            Activity newActivity = activityService.saveActivity(activity);
            return ResponseEntity.ok(newActivity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // To edit the activity details
    @PutMapping("/{activityName}/edit")
    public ResponseEntity<?> editActivity(@PathVariable String activityName, @RequestBody Activity activity) {
        try {
            Activity updatedActivity = activityService.updateActivity(activityName, activity);
            return ResponseEntity.ok(updatedActivity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // information about a particular activity
    @GetMapping("/{activityName}")
    public ResponseEntity<?> getActivity(@PathVariable String activityName) {
        try {
            Activity activity = activityService.getActivity(activityName);
            return ResponseEntity.ok(activity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // information about all activities that are available (capacity > 0)
    @GetMapping("/availableActivities")
    public ResponseEntity<?> getAllAvailableActivities() {
        try {
            List<Activity> activity = activityService.getAllAvailableActivities();
            return new ResponseEntity<>(activity, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
