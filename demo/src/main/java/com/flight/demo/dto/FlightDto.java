package com.flight.demo.dto;

import com.flight.demo.model.Airport;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FlightDto {
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String price;
}
