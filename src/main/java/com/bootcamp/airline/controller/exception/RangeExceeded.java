package com.bootcamp.airline.controller.exception;

public class RangeExceeded extends RuntimeException {

    public RangeExceeded() {
        super("Plane range is too short for route distance.");     }


    public RangeExceeded(String message) {
        super(message);
    }
}

