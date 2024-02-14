package com.flight.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Autowired
    private FlightService flightService;

    @Scheduled(cron = "0 0 0 * * *")
    public void execute(){
        flightService.saveFlightsToDb();
    }
}
