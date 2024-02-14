package com.flight.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection="flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    private ObjectId id;
    @DBRef
    private Airport departureAirport;
    @DBRef
    private Airport arrivalAirport;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String price;
}
