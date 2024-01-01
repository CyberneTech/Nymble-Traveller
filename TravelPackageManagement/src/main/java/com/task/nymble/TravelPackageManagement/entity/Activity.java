package com.task.nymble.TravelPackageManagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "destination_Id")
    private Destination destination;

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
