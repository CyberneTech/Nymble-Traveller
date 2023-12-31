package com.task.nymble.TravelPackageManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.task.nymble.TravelPackageManagement.entity")
public class TravelPackageManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelPackageManagementApplication.class, args);
	}

}
