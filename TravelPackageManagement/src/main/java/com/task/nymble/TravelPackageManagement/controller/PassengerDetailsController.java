package com.task.nymble.TravelPackageManagement.controller;

import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.service.PassengerDetailsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import static com.task.nymble.TravelPackageManagement.service.Constants.WALLET_AMOUNT;

@RestController
@RequestMapping("/api/v1/passenger")
public class PassengerDetailsController {

    @Autowired
    private PassengerDetailsService passengerDetailsService;

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengerList= passengerDetailsService.getAllPassengerDetails();
        return ResponseEntity.ok(passengerList);
    }

    // Endpoint to create a new passenger
    @PostMapping("/new")
    public ResponseEntity<?> createPassenger(@RequestBody Passenger passenger) {
        try {
            Passenger newPassenger = passengerDetailsService.createPassenger(passenger);
            return ResponseEntity.ok(newPassenger);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to recharge a passenger's wallet
    @PutMapping("/{passengerNumber}/recharge")
    public ResponseEntity<?> rechargeWallet(@PathVariable Long passengerNumber, @RequestBody Map<String, Object> body) {
        try {
            Passenger updatedPassenger = passengerDetailsService.rechargeWallet(passengerNumber, (Integer) body.get(WALLET_AMOUNT));
            return ResponseEntity.ok(updatedPassenger);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to get details of a passenger
    @GetMapping("/{passengerNumber}")
    public ResponseEntity<Passenger> getPassengerDetails(@PathVariable Long passengerNumber) {
        Passenger passenger = passengerDetailsService.getPassengerDetails(passengerNumber);
        if (passenger != null) {
            return ResponseEntity.ok(passenger);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint to get all activities enrolled by passenger and amount paid
    @GetMapping("/{passengerNumber}/activityBookingDetails")
    public ResponseEntity<?> getPassengerActivityBookingDetails(@PathVariable Long passengerNumber) {
        try {
            Map<String, Object> activityBookingDetail = passengerDetailsService.getPassengerActivityBookingDetails(passengerNumber);
            return ResponseEntity.ok(new JSONObject(activityBookingDetail).toString());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
