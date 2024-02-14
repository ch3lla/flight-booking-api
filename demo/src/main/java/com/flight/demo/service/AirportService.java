package com.flight.demo.service;

import com.flight.demo.dto.AirportDto;
import com.flight.demo.model.Airport;
import com.flight.demo.repository.AirportRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;
    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(ObjectId airportId){
        return airportRepository.findById(airportId);
    }
    public Optional<Airport> getAirportByCity(String city){
        return airportRepository.findByCity(city);
    }
    public Airport createAirport(AirportDto airportDto){
        Airport airport = new Airport();
        airport.setCity(airportDto.getCity());
        return airportRepository.save(airport);
    }
    public Airport UpdateAirport(ObjectId airportId, AirportDto airportDto){
        Optional<Airport> optionalAirport  = airportRepository.findById(airportId);
        if (optionalAirport .isPresent()){
            Airport airportFromDb = optionalAirport.get();
            airportFromDb.setCity(airportDto.getCity());
            return airportRepository.save(airportFromDb);
        }
        return null;
    }

    public void deleteAirport(ObjectId airportId){
        airportRepository.deleteById(airportId);
    }
}
