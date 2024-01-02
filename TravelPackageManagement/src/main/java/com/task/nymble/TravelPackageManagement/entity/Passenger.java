package com.task.nymble.TravelPackageManagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import com.task.nymble.TravelPackageManagement.service.Constants.MembershipType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents passengers data. List of users on the application
 * A passenger can book multiple activities and enroll in various travel packages.
 * creates -> table("passenger_details)  and another JOIN table("package_booking") with TravelPackages
 */

@Entity
@Table(name = "passenger_details")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "passengerNumber"
)
@Getter
@Setter
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passengerNumber;

    @Column(nullable = false)
    private String passengerName;

    private double walletBalance;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    @JsonIgnore
    @OneToMany(mappedBy = "passenger")
    private List<ActivityBooking> activityBookings;

    //created a JOIN table package_booking
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "package_booking",
            joinColumns = @JoinColumn(name = "passenger_number"),
            inverseJoinColumns = @JoinColumn(name = "travel_package_id"))
    private List<TravelPackage> travelPackages;

    public Passenger(String passengerName, double walletBalance, MembershipType membershipType) {
        this.passengerName = passengerName;
        this.walletBalance = walletBalance;
        this.membershipType = membershipType;
        this.travelPackages = new ArrayList<>();
    }

    public Passenger(String passengerName, MembershipType membershipType) {
        this.passengerName = passengerName;
        this.membershipType = membershipType;
        this.walletBalance = 0;
        this.travelPackages = new ArrayList<>();
    }

    public Passenger(String passengerName, int walletBalance) {
        this.passengerName = passengerName;
        this.walletBalance = walletBalance;
        this.membershipType = MembershipType.STANDARD;
        this.travelPackages = new ArrayList<>();
    }

    public Passenger(String passengerName) {
        this.passengerName = passengerName;
        this.walletBalance = 0;
        this.membershipType = MembershipType.STANDARD;
        this.travelPackages = new ArrayList<>();
    }

    public Passenger() {
        this.passengerName = "Guest";
        this.walletBalance = 0;
        this.membershipType = MembershipType.STANDARD;
        this.travelPackages = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerNumber=" + passengerNumber +
                ", passengerName='" + passengerName + '\'' +
                ", walletBalance=" + walletBalance +
                ", membershipType=" + membershipType +
                '}';
    }
}
