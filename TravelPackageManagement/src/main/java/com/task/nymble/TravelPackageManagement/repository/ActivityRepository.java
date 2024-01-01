package com.task.nymble.TravelPackageManagement.repository;

import com.task.nymble.TravelPackageManagement.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

    //SELECT activities from activities WHERE activities.capacity > capacity;
    List<Activity> findByCapacityGreaterThan(int capacity);

}
