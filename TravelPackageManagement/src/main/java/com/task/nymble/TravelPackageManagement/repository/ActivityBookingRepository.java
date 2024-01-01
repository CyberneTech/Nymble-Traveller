package com.task.nymble.TravelPackageManagement.repository;

import com.task.nymble.TravelPackageManagement.entity.ActivityBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityBookingRepository extends JpaRepository<ActivityBooking, String> {

}
