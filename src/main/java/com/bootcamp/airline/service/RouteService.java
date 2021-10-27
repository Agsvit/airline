package com.bootcamp.airline.service;

import com.bootcamp.airline.controller.exception.AirportNotFound;
import com.bootcamp.airline.controller.exception.PlaneNotFound;
import com.bootcamp.airline.controller.exception.RouteNotFound;
import com.bootcamp.airline.model.Airport;
import com.bootcamp.airline.model.Plane;
import com.bootcamp.airline.model.Route;
import com.bootcamp.airline.repository.AirportRepository;
import com.bootcamp.airline.repository.PlaneRepository;
import com.bootcamp.airline.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final PlaneRepository planeRepository;
    private final AirportRepository airportRepository;

    public RouteService(RouteRepository routeRepository, PlaneRepository planeRepository, AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.planeRepository = planeRepository;
        this.airportRepository = airportRepository;
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public Route findById(Long id) {
        return routeRepository.findById(id).orElseThrow(RouteNotFound::new);
    }

    public Route save(Route route, Long airportId, Long planeId) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(AirportNotFound::new);
        route.setAirport(airport);
        return routeRepository.save(route);
    }

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }

    public Route update(Route route) {
        Route newRoute = this.findById(route.getId());
        return routeRepository.save(newRoute);
    }
}
