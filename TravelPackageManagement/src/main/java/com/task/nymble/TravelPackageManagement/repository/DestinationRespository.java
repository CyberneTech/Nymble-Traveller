package com.task.nymble.TravelPackageManagement.repository;

import com.task.nymble.TravelPackageManagement.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRespository extends JpaRepository<Destination, Long> {
}
