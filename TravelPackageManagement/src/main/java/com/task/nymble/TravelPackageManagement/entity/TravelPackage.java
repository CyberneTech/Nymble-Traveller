package com.task.nymble.TravelPackageManagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="travelpackage")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "travelPackageId"
)
@Getter
@Setter
@NoArgsConstructor
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelPackageId;

    @Column(nullable = false)
    private String travelPackageName;

    @Column(nullable = false)
    private Integer passengerCapacity;

    //counter for the number for passengers enrolled
    private Integer totalEnrollments = 0;

    @JsonIgnore
    @ManyToMany(mappedBy = "travelPackages")
    private List<Passenger> passengers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "package_destinations",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id"))
    private List<Destination> destinations = new ArrayList<>();

    public TravelPackage(String travelPackageName, Integer passengerCapacity) {
        this.travelPackageName = travelPackageName;
        this.passengerCapacity = passengerCapacity;
    }

    public void increaseCurrentCapacity() {
        if (totalEnrollments < passengerCapacity) {
            totalEnrollments++;
        } else {
            throw new IllegalStateException("Cannot exceed passenger capacity.");
        }
    }
}
