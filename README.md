# FLIGHT BOOKING API

## Introduction
This is a Flight Booking API built using Spring Boot, Maven, and Java. It provides endpoints for managing flights, airports, and user authentication.

## Main Technologies
1. **Spring Boot**: Provides a powerful framework for building web applications and microservices in Java.
2. **Maven**: A build automation tool used for managing dependencies and building projects in Java.
3. **Java**: The programming language used to implement the backend logic of the API.

## Ideas Implemented
1. **Cron Job**: Scheduled tasks to automate processes such as updating flight statuses or sending notifications.
2. **User Authentication**: Secure endpoints and authenticate users using JSON Web Tokens (JWT).

## Main Classes
1. **Flight**: Represents a flight entity with details such as departure and arrival airports, date, and status.
2. **Airport**: Represents an airport entity with details such as code, name, and location.

## Getting Started
To run the API locally:

1. Clone this repository.
2. Navigate to the project directory.
3. Configure the database connection in the `application.properties` file.
4. Build the project using Maven: `mvn clean install`.
5. Run the application: `java -jar target/flight-booking-api.jar`.
6. Access the API endpoints using a tool like Postman or curl.
