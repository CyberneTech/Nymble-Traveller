package com.task.nymble.TravelPackageManagement.repository;

import com.task.nymble.TravelPackageManagement.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
}
