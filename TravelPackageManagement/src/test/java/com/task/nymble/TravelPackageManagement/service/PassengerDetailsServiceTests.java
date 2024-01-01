package com.task.nymble.TravelPackageManagement.service;
import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.repository.PassengerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PassengerDetailsServiceTests {

    @InjectMocks
    private PassengerDetailsService passengerDetailsService;

    @Mock
    private PassengerRepository passengerRepository;

    @Test
    public void testCreatePassenger() {

        Passenger passenger = new Passenger();
        // Mocking the behavior of the repository
        Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

        Passenger result = passengerDetailsService.createPassenger(passenger);

        // Assert
        Assertions.assertEquals(passenger, result);

        // Verify that the repository save method was called
        Mockito.verify(passengerRepository).save(passenger);
    }

    @Test
    public void testRechargeWallet() throws Exception {

        Long passengerNumber = 1L;
        int amount = 100;

        Passenger passenger = new Passenger();
        passenger.setPassengerNumber(passengerNumber);
        passenger.setWalletBalance(200);

        // Mocking the behavior of the repository
        Mockito.when(passengerRepository.findById(passengerNumber)).thenReturn(java.util.Optional.of(passenger));
        Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

        // Get wallet balance before recharge
        double balance = passenger.getWalletBalance();

        Passenger result = passengerDetailsService.rechargeWallet(passengerNumber, amount);

        // Assert
        Assertions.assertEquals(balance + amount, result.getWalletBalance());

        // Verify that the repository findById and save methods were called
        Mockito.verify(passengerRepository).findById(passengerNumber);
        Mockito.verify(passengerRepository).save(passenger);
    }

    @Test
    public void testGetPassengerDetails() {

        Long passengerNumber = 1L;
        Passenger passenger = new Passenger();

        // Mocking the behavior of the repository
        Mockito.when(passengerRepository.findById(passengerNumber)).thenReturn(java.util.Optional.of(passenger));

        Passenger result = passengerDetailsService.getPassengerDetails(passengerNumber);

        // Assert
        Assertions.assertEquals(passenger, result);

        // Verify that the repository findById method was called
        Mockito.verify(passengerRepository).findById(passengerNumber);
    }
}

