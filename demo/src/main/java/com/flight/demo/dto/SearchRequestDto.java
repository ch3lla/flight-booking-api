package com.flight.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequestDto {
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String price;
    private String FlightType;
}
