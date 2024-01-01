package com.task.nymble.TravelPackageManagement.controller;

import com.task.nymble.TravelPackageManagement.service.TravelPackageService;
import com.task.nymble.TravelPackageManagement.entity.TravelPackage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.task.nymble.TravelPackageManagement.service.Constants.PASSENGER_NUMBER;
import static com.task.nymble.TravelPackageManagement.service.Constants.PACKAGE_ID;

@RestController
@RequestMapping("/api/v1/package")
public class TravelPackageController {

    @Autowired
    private TravelPackageService travelPackageService;

    @GetMapping
    public ResponseEntity<List<TravelPackage>> getAllTravelPackage() {
            List<TravelPackage> travelPackages = travelPackageService.getAllTravelPackages();
            return ResponseEntity.ok(travelPackages);
    }

    @GetMapping("/{travelPackageId}")
    public ResponseEntity<?> getTravelPackageForId(@PathVariable Long travelPackageId) {
        try {
            TravelPackage travelPackage = travelPackageService.getTravelPackage(travelPackageId);
            return ResponseEntity.ok(travelPackage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<TravelPackage> createTravelPackage(@RequestBody TravelPackage travelPackage) {
        TravelPackage savedTravelPackage = travelPackageService.saveTravelPackage(travelPackage);
        return ResponseEntity.ok(savedTravelPackage);
    }

    @PutMapping("/{travelPackageId}/edit")
    public ResponseEntity<?> updateTravelPackageForId(@PathVariable Long travelPackageId, @RequestBody TravelPackage travelPackage) {
        try {
            TravelPackage updatedTravelPackage = travelPackageService.updateTravelPackage(travelPackageId, travelPackage);
            return ResponseEntity.ok(updatedTravelPackage);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{travelPackageId}/itinerary")
    public ResponseEntity<?> printTravelPackageItinerary(@PathVariable Long travelPackageId) {
        try {
            Map<String, Object> response = travelPackageService.getTravelPackageItinerary(travelPackageId);
            return ResponseEntity.ok(new JSONObject(response).toString());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{travelPackageId}/passengerList")
    public ResponseEntity<?> printTravelPackagepassengerList(@PathVariable Long travelPackageId) {
        try {
            Map<String, Object> response = travelPackageService.getTravelPackagePassengerList(travelPackageId);
            return ResponseEntity.ok(new JSONObject(response).toString());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/bookPackage")
    public ResponseEntity<?> bookTravelPackage(@RequestBody Map<String, Object> body) {
        Long travelPackageId = ((Integer) body.get(PACKAGE_ID)).longValue();
        Long passengerNumber = ((Integer) body.get(PASSENGER_NUMBER)).longValue();
        try {
            String response = travelPackageService.bookTravelPackageService(travelPackageId, passengerNumber);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
