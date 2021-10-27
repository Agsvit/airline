package com.bootcamp.airline.service;

import com.bootcamp.airline.controller.exception.AirportNotFound;
import com.bootcamp.airline.controller.exception.PlaneNotFound;
import com.bootcamp.airline.controller.exception.RangeExceeded;
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
public class PlaneService {
    
    private final PlaneRepository planeRepository;
    private final AirportRepository airportRepository;
    private final RouteRepository routeRepository;

    public PlaneService(PlaneRepository planeRepository, AirportRepository airportRepository, RouteRepository routeRepository) {
        this.planeRepository = planeRepository;
        this.airportRepository = airportRepository;
        this.routeRepository = routeRepository;
    }

    public List<Plane> findAll() {
        return planeRepository.findAll();
    }

    public Plane findById(Long id) {
        return planeRepository.findById(id).orElseThrow(PlaneNotFound::new);
    }

    public Plane save(Plane plane, Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(AirportNotFound::new);
        plane.setAirport(airport);
        return planeRepository.save(plane);
    }

    public void deleteById(Long id) {
        planeRepository.deleteById(id);
    }

    public Plane update(Plane plane, Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(AirportNotFound::new);
        Plane newPlane = this.findById(plane.getId());
        newPlane.setAirport(airport);
        return planeRepository.save(newPlane);
    }

    public Plane addRouteToPlane(Long routeId, Long planeId) {
        Route route = routeRepository.findById(routeId).orElseThrow(RouteNotFound::new);
        Plane plane = planeRepository.findById(planeId).orElseThrow(PlaneNotFound::new);
        if (plane.getMaxRange() >= route.getDistance()) {
            plane.getRoutes().add(route);
            return planeRepository.save(plane);
        } else {
            throw new RangeExceeded();
        }
    }
}
