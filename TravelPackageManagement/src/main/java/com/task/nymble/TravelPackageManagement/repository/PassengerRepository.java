package com.task.nymble.TravelPackageManagement.repository;

import com.task.nymble.TravelPackageManagement.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
