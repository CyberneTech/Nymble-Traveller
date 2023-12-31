package com.task.nymble.TravelPackageManagement.entity;

import jakarta.persistence.*;

import com.task.nymble.TravelPackageManagement.service.Constants.MembershipType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passenger_details")
@Getter
@Setter
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passengerNumber;

    @Column(nullable = false)
    private String passengerName;

    private int walletBalance;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    public Passenger(String passengerName, int walletBalance, MembershipType membershipType) {
        this.passengerName = passengerName;
        this.walletBalance = walletBalance;
        this.membershipType = membershipType;
    }

    public Passenger(String passengerName, MembershipType membershipType) {
        this.passengerName = passengerName;
        this.membershipType = membershipType;
        this.walletBalance = 0;
    }

    public Passenger(String passengerName, int walletBalance) {
        this.passengerName = passengerName;
        this.walletBalance = walletBalance;
        this.membershipType = MembershipType.STANDARD;
    }

    public Passenger(String passengerName) {
        this.passengerName = passengerName;
        this.walletBalance = 0;
        this.membershipType = MembershipType.STANDARD;
    }

    public Passenger() {
        this.passengerName = "Guest";
        this.walletBalance = 0;
        this.membershipType = MembershipType.STANDARD;
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
