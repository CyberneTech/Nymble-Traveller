package com.task.nymble.TravelPackageManagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "passenger_number")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "activity_name", nullable = false)
    private Activity activity;

    public ActivityBooking(double amountPaid){
        this.amountPaid = amountPaid;
    }
}
