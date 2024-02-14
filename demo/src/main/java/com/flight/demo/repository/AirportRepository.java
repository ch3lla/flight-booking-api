package com.flight.demo.repository;

import com.flight.demo.model.Airport;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AirportRepository extends MongoRepository<Airport, ObjectId> {
    Optional<Airport> findByCity(String city);
}
