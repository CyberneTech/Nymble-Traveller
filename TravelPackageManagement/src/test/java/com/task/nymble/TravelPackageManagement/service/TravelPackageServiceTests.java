package com.task.nymble.TravelPackageManagement.service;

import com.task.nymble.TravelPackageManagement.entity.TravelPackage;
import com.task.nymble.TravelPackageManagement.repository.PassengerRepository;
import com.task.nymble.TravelPackageManagement.repository.TravelPackageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TravelPackageServiceTests {

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private TravelPackageService travelPackageService;

    @Test
    void saveTravelPackage() {
        TravelPackage travelPackage = new TravelPackage("Package 1", 10);

        when(travelPackageRepository.save(any(TravelPackage.class))).thenReturn(travelPackage);
        TravelPackage savedPackage = travelPackageService.saveTravelPackage(travelPackage);

        assertNotNull(savedPackage);
        assertEquals("Package 1", savedPackage.getTravelPackageName());
        assertEquals(10, savedPackage.getPassengerCapacity());
    }

    @Test
    void updateTravelPackage_Exists() throws Exception {
        TravelPackage existingPackage = new TravelPackage("Package 1", 10);
        existingPackage.setTravelPackageId(1L);

        TravelPackage updatedPackage = new TravelPackage("Updated Package", 15);

        when(travelPackageRepository.existsById(1L)).thenReturn(true);
        when(travelPackageRepository.save(any(TravelPackage.class))).thenReturn(updatedPackage);
        TravelPackage result = travelPackageService.updateTravelPackage(1L, updatedPackage);

        assertNotNull(result);
        assertEquals("Updated Package", result.getTravelPackageName());
        assertEquals(15, result.getPassengerCapacity());
    }

    @Test
    void updateTravelPackage_NotExists() {
        TravelPackage updatedPackage = new TravelPackage("Updated Package", 15);

        when(travelPackageRepository.existsById(1L)).thenReturn(false);
        assertThrows(Exception.class, () -> travelPackageService.updateTravelPackage(1L, updatedPackage));
    }

    @Test
    void getTravelPackage() {
        TravelPackage expectedPackage = new TravelPackage("Package 1", 10);
        expectedPackage.setTravelPackageId(1L);

        when(travelPackageRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedPackage));
        TravelPackage result = travelPackageService.getTravelPackage(1L);

        assertNotNull(result);
        assertEquals("Package 1", result.getTravelPackageName());
        assertEquals(10, result.getPassengerCapacity());
    }

    @Test
    void getAllTravelPackages() {
        List<TravelPackage> packages = new ArrayList<>();
        packages.add(new TravelPackage("Package 1", 10));
        packages.add(new TravelPackage("Package 2", 15));

        when(travelPackageRepository.findAll()).thenReturn(packages);
        List<TravelPackage> result = travelPackageService.getAllTravelPackages();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getTravelPackageItinerary() {
        TravelPackage travelPackage = new TravelPackage("Package 1", 10);
        travelPackage.setTravelPackageId(1L);

        when(travelPackageRepository.findById(1L)).thenReturn(java.util.Optional.of(travelPackage));
        Map<String, Object> result = travelPackageService.getTravelPackageItinerary(1L);

        assertNotNull(result);
        assertEquals("Package 1", result.get("packageName"));
    }

    @Test
    void getTravelPackageItinerary_NotFound() {
        when(travelPackageRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> travelPackageService.getTravelPackageItinerary(1L));
    }
}

