package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassengerDetailsService {
    @Autowired
    private PassengerRepository passengerRepository;

    //service to create a new passenger entry
    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    // service to add balance to wallet of a user
    @Transactional
    public Passenger rechargeWallet(Long passengerNumber, int amount) throws Exception {
        // to maintain ACID properties of transaction
        Passenger passenger = passengerRepository.findById(passengerNumber)
                .orElseThrow(() -> new Exception("Passenger not found"));
        passenger.setWalletBalance(passenger.getWalletBalance() + amount);
        return passengerRepository.save(passenger);
    }

    // To fetch and return passenger details
    public Passenger getPassengerDetails(Long passengerNumber) {
        return passengerRepository.findById(passengerNumber).orElse(null);
    }
}
