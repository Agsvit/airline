package com.bootcamp.airline.service;

import com.bootcamp.airline.controller.exception.AirportNotFound;
import com.bootcamp.airline.controller.response.AirportResponse;
import com.bootcamp.airline.model.Airport;
import com.bootcamp.airline.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    public Airport findById(Long id) {
        return airportRepository.findById(id).orElseThrow(AirportNotFound::new);
    }

    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    public Airport update(Airport airport) {
        Airport newAirport = this.findById(airport.getId());
        return airportRepository.save(newAirport);
    }
}
