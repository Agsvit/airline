package com.bootcamp.airline.controller.exception;

public class AirportNotFound extends RuntimeException {

    public AirportNotFound() {
        super("Airport not found");     }

    public AirportNotFound(String message) {
        super(message);
    }
}

