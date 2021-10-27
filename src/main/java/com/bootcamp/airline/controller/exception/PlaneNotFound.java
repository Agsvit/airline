package com.bootcamp.airline.controller.exception;

public class PlaneNotFound extends RuntimeException {

    public PlaneNotFound() {
        super("Plane not found");     }


    public PlaneNotFound(String message) {
        super(message);
    }
}

