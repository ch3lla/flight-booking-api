package com.flight.demo.repository;

import com.flight.demo.model.Airport;
import com.flight.demo.model.Flight;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends MongoRepository<Flight, ObjectId> {
    List<Flight> findByDepartureAirport(Airport departureAirport);
    List<Flight> findByArrivalAirport(Airport arrivalAirport);
    List<Flight> findByDepartureDate (LocalDate departureDate);
    List<Flight> findByReturnDate (LocalDate returnDate);
    List<Flight> findByReturnDateIsNull();
}
