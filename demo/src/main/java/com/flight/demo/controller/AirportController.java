package com.flight.demo.controller;

import com.flight.demo.dto.AirportDto;
import com.flight.demo.model.Airport;
import com.flight.demo.service.AirportService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;
    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody AirportDto airportDto){
        return new ResponseEntity<>(airportService.createAirport(airportDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports(){
        return new ResponseEntity<>(airportService.getAllAirports(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Airport>> getSingleAirport(@PathVariable ObjectId id){
        return new ResponseEntity<>(airportService.getAirportById(id), HttpStatus.OK);
    }
    @GetMapping("/{city}")
    public ResponseEntity<Optional<Airport>> getSingleAirport(@PathVariable String city){
        return new ResponseEntity<>(airportService.getAirportByCity(city), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirportCity(@PathVariable ObjectId id, @RequestBody AirportDto airportDto){
        return new ResponseEntity<>(airportService.UpdateAirport(id, airportDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable ObjectId id){
        airportService.deleteAirport(id);
    }

}
