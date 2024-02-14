package com.flight.demo.controller;

import com.flight.demo.dto.FlightDto;
import com.flight.demo.dto.SearchRequestDto;
import com.flight.demo.model.Flight;
import com.flight.demo.repository.FlightRepository;
import com.flight.demo.service.FlightService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDto flightDto){
        return new ResponseEntity<>(flightService.createFlight(flightDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Flight>> getSingleFlight (@PathVariable ObjectId id){
        return new ResponseEntity<>(flightService.getFlightById(id), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchByFlightDetails(@RequestBody SearchRequestDto requestDto){
        if (requestDto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(flightService.searchFlights(requestDto), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlightDetails(@PathVariable ObjectId id, @RequestBody FlightDto flightDto){
        return new ResponseEntity<>(flightService.updateFlight(id, flightDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable ObjectId id){
        flightService.deleteFlight(id);
    }
}
