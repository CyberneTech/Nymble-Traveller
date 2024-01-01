package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import com.task.nymble.TravelPackageManagement.entity.ActivityBooking;
import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.repository.ActivityBookingRepository;
import com.task.nymble.TravelPackageManagement.repository.PassengerRepository;
import com.task.nymble.TravelPackageManagement.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ActivityBookingServiceTests {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ActivityBookingRepository activityBookingRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private ActivityBookingService activityBookingService;

    @Test
    void testBookActivity_SuccessfulBooking() {
        // Mock data
        Passenger passenger = new Passenger("John", Constants.MembershipType.PREMIUM);
        Activity activity = new Activity("Swimming", "Water activity", 50, 10, null);

        when(passengerRepository.findById(any(Long.class))).thenReturn(Optional.of(passenger));
        when(activityRepository.findById(any(String.class))).thenReturn(Optional.of(activity));

        // Perform the booking
        activityBookingService.bookActivity("Swimming", 1L);

        // Verify that save methods were called
        verify(passengerRepository, times(1)).save(any(Passenger.class));
        verify(activityRepository, times(1)).save(any(Activity.class));
        verify(activityBookingRepository, times(1)).save(any(ActivityBooking.class));
    }

    @Test
    void testBookActivity_InsufficientBalance() {
        // Mock data
        Passenger passenger = new Passenger("John", 30, Constants.MembershipType.STANDARD);
        Activity activity = new Activity("Swimming", "Water activity", 50, 10, null);

        when(passengerRepository.findById(any(Long.class))).thenReturn(Optional.of(passenger));
        when(activityRepository.findById(any(String.class))).thenReturn(Optional.of(activity));

        // Perform the booking and expect an exception
        assertThrows(IllegalStateException.class, () -> activityBookingService.bookActivity("Swimming", 1L));

        // Verify that save methods were not called
        verify(passengerRepository, never()).save(any(Passenger.class));
        verify(activityRepository, never()).save(any(Activity.class));
        verify(activityBookingRepository, never()).save(any(ActivityBooking.class));
    }

    @Test
    void testBookActivity_CapacityReached() {
        // Mock data
        Passenger passenger = new Passenger("John", Constants.MembershipType.PREMIUM);
        Activity activity = new Activity("Swimming", "Water activity", 50, 0, null);

        when(passengerRepository.findById(any(Long.class))).thenReturn(Optional.of(passenger));
        when(activityRepository.findById(any(String.class))).thenReturn(Optional.of(activity));

        // Perform the booking and expect an exception
        assertThrows(IllegalStateException.class, () -> activityBookingService.bookActivity("Swimming", 1L));

        // Verify that save methods were not called
        verify(passengerRepository, never()).save(any(Passenger.class));
        verify(activityRepository, never()).save(any(Activity.class));
        verify(activityBookingRepository, never()).save(any(ActivityBooking.class));
    }
}

