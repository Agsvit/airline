package com.bootcamp.airline.controller.exception;

public class RouteNotFound extends RuntimeException {

    public RouteNotFound() {
        super("Route not found");     }


    public RouteNotFound(String message) {
        super(message);
    }
}

