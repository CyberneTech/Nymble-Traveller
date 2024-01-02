package com.task.nymble.TravelPackageManagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a booking of an activity by a passenger. It links the activity and the passenger who booked it.
 * many-to-one relation with both passenger_details, activities
 */

@Entity
@Table(name="activity_booking")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookingId"
)
@Getter
@Setter
@NoArgsConstructor
public class ActivityBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;

    private double amountPaid;

    //foreign key of passenger_details
    @ManyToOne
    @JoinColumn(name = "passenger_number")
    private Passenger passenger;

    //foreign key of activites
    @ManyToOne
    @JoinColumn(name = "activity_name", nullable = false)
    private Activity activity;

    public ActivityBooking(double amountPaid){
        this.amountPaid = amountPaid;
    }
}
