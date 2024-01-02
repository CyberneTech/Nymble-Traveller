package com.task.nymble.TravelPackageManagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

/**
 * Entity to represents an activity in a travel package.
 * Each activity is associated with a specific destination (many-to-one relation with destination)
 * one-to-many relaiton with activity booking
 */

@Entity
@Table(name = "activities")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "activityName"
)
@Getter
@Setter
@NoArgsConstructor
public class Activity {
    @Id
    private String activityName;

    private String description;

    private int cost = 0;

    @Column(nullable = false)
    private Integer capacity;

    //(foreign-key) of destinations table
    @ManyToOne
    @JoinColumn(name = "destination_Id")
    private Destination destination;

    // Bidirectional mapping with activity_booking
    @JsonIgnore
    @OneToMany(mappedBy = "activity")
    private List<ActivityBooking> activityBookingList;

    public Activity(String activityName, String description, int cost, int capacity, Destination destination) {
        this.activityName = activityName;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityName='" + activityName + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", capacity=" + capacity + '\'' +
                '}';
    }
}
