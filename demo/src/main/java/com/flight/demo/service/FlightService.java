package com.flight.demo.service;

import com.flight.demo.dto.AirportDto;
import com.flight.demo.dto.FlightDto;
import com.flight.demo.dto.SearchRequestDto;
import com.flight.demo.model.Airport;
import com.flight.demo.model.Flight;
import com.flight.demo.repository.AirportRepository;
import com.flight.demo.repository.FlightRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    public List<Flight> searchFlights(SearchRequestDto searchRequest) {
        System.out.println(searchRequest);
        if (searchRequest.getDepartureAirport() != null){
            Airport departureAirport = airportRepository.findByCity(searchRequest.getDepartureAirport()).orElse(null);
            return flightRepository.findByDepartureAirport(departureAirport);
        }
        if (searchRequest.getArrivalAirport() != null){
            Airport arrivalAirport = airportRepository.findByCity(searchRequest.getArrivalAirport()).orElse(null);
            return flightRepository.findByArrivalAirport(arrivalAirport);
        }
        if(searchRequest.getDepartureDate() != null){
            LocalDate departureDateTime = LocalDate.parse(searchRequest.getDepartureDate().toString(), DateTimeFormatter.ISO_DATE);
            return flightRepository.findByDepartureDate(departureDateTime);
        }
        if (searchRequest.getReturnDate() != null) {
            LocalDate returnDateTime = LocalDate.parse(searchRequest.getReturnDate().toString(), DateTimeFormatter.ISO_DATE);
            return flightRepository.findByReturnDate(returnDateTime);
        }
        if (searchRequest.getFlightType() != null){
            if (searchRequest.getFlightType().equals("One-way-flight")){
                return flightRepository.findByReturnDateIsNull();
            }
        }
        return null;
    }

    public Optional<Flight> getFlightById(ObjectId flightId){
        return flightRepository.findById(flightId);
    }
    public Flight createFlight(FlightDto flightDto){
        Flight flight = new Flight();
        Airport deptAirPort = airportRepository.findByCity(flightDto.getDepartureAirport()).orElse(null);
        Airport arrivAirport = airportRepository.findByCity(flightDto.getArrivalAirport()).orElse(null);
        flight.setDepartureAirport(deptAirPort);
        flight.setArrivalAirport(arrivAirport);
        flight.setDepartureDate(flightDto.getDepartureDate());
        flight.setReturnDate(flightDto.getReturnDate());
        flight.setPrice(flightDto.getPrice());
        return flightRepository.save(flight);
    }

    public Flight updateFlight(ObjectId flightId, FlightDto flightDto){
        Optional<Flight> flightFromDb = flightRepository.findById(flightId);
        if (flightFromDb.isPresent()){
            Flight flight = flightFromDb.get();
            Airport deptAirPort = airportRepository.findByCity(flightDto.getDepartureAirport()).orElse(null);
            Airport arrivAirport = airportRepository.findByCity(flightDto.getArrivalAirport()).orElse(null);
            flight.setDepartureAirport(deptAirPort);
            flight.setArrivalAirport(arrivAirport);
            flight.setDepartureDate(flightDto.getDepartureDate());
            flight.setReturnDate(flightDto.getReturnDate());
            return flightRepository.save(flight);
        }
        return null;
    }

    public void deleteFlight(ObjectId flightId){
        flightRepository.deleteById(flightId);
    }

    public void saveFlightsToDb() {
        String url = "https://run.mocky.io/v3/0045586f-79aa-4920-80f0-90f4f96fcea9";
        WebClient.Builder builder = WebClient.builder();

        List<FlightDto> flightDtos = builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(FlightDto.class)
                .collectList()
                .block();

        if (flightDtos != null) {
            for (FlightDto flightDto : flightDtos) {
                createFlight(flightDto);
            }
        }
    }

}
