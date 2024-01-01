package com.task.nymble.TravelPackageManagement.controller;


import com.task.nymble.TravelPackageManagement.entity.ActivityBooking;
import com.task.nymble.TravelPackageManagement.service.ActivityBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.task.nymble.TravelPackageManagement.service.Constants.ACTIVITY_NAME;
import static com.task.nymble.TravelPackageManagement.service.Constants.PASSENGER_NUMBER;

@RestController
@RequestMapping("api/v1/bookActivity")
public class ActivityBookingController {

    @Autowired
    private ActivityBookingService activityBookingService;

    @GetMapping
    public ResponseEntity<List<ActivityBooking>> getAllActivityBookings() {
        List<ActivityBooking> activityBookings = activityBookingService.getAllActivityBookings();
        return ResponseEntity.ok(activityBookings);
    }

    @PostMapping("/new")
    public ResponseEntity<?> bookActivity(@RequestBody Map<String, Object> requestBody) {
        try {
            String activityName = requestBody.get(ACTIVITY_NAME).toString();
            Long passengerNumber = ((Integer) requestBody.get(PASSENGER_NUMBER)).longValue();
            activityBookingService.bookActivity(activityName, passengerNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
