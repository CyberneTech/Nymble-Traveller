package com.task.nymble.TravelPackageManagement.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.nymble.TravelPackageManagement.entity.Passenger;
import com.task.nymble.TravelPackageManagement.service.PassengerDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebMvcTest(PassengerDetailsController.class)
public class PassengerDetailsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerDetailsService passengerDetailsService;

    @Test
    public void testRechargeWallet() throws Exception {
        Long passengerNumber = 1L;
        int amount = 100;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", amount);

        Passenger updatedPassenger = new Passenger();
        when(passengerDetailsService.rechargeWallet(passengerNumber, amount)).thenReturn(updatedPassenger);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/passenger/{passengerNumber}/recharge", passengerNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPassengerDetails() throws Exception {
        Long passengerNumber = 1L;
        Passenger passenger = new Passenger();

        when(passengerDetailsService.getPassengerDetails(passengerNumber)).thenReturn(passenger);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/passenger/{passengerNumber}", passengerNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Helper method to convert an object to JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
