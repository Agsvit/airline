package com.bootcamp.airline.controller;

import com.bootcamp.airline.controller.request.AirportRequest;
import com.bootcamp.airline.controller.response.AirportResponse;
import com.bootcamp.airline.model.Airport;
import com.bootcamp.airline.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    public List<AirportResponse> airportResponses(List<Airport> airports) {
        List<AirportResponse> airportResponses = new ArrayList<>();
        for (Airport airport : airports) {airportResponses.add(airport.airportResponse());}
        return airportResponses;
    }

    @GetMapping("/airports")
    public List<AirportResponse> getAirports() {
        return this.airportResponses(airportService.findAll());
    }

    @GetMapping("/airports/{id}")
    public AirportResponse getAirportById(@PathVariable(value = "id") Long id) {
        return airportService.findById(id).airportResponse();
    }

    @PostMapping(value = "/airports", consumes = "application/json", produces = "application/json")
    public AirportResponse createAirport(@RequestBody AirportRequest airportRequest) {
        return airportService.save(airportRequest.airportBuild()).airportResponse();
    }

    @PutMapping(value = "/airports-update")
    public AirportResponse updateAirport(@RequestBody Long id,  AirportRequest airportRequest) {
        return airportService.update(airportRequest.airportUp(id)).airportResponse();
    }

    @DeleteMapping(value = "/airports-delete")
    public void deletePet(@RequestBody Long id) {
        airportService.deleteById(id);
    }

}
