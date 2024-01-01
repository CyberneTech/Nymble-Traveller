package com.task.nymble.TravelPackageManagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="destinations")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "destinationId"
)
@Getter
@Setter
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long destinationId;

    private String destinationName;

    @JsonIgnore
    @OneToMany(mappedBy = "destination", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private  List<Activity> activities;

    @JsonIgnore
    @ManyToMany(mappedBy = "destinations")
    private List<TravelPackage> travelPackages;

    public Destination(String destinationName){
        this.destinationName = destinationName;
        this.activities = new ArrayList<>();
        this.travelPackages = new ArrayList<>();
    }
    @Override
    public String toString(){
        return "Destination{" +
                "destinationId="+destinationId+ '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", activities=" + activities.toString() +
                '}';
    }
}
