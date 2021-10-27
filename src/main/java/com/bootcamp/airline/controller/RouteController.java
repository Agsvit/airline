package com.bootcamp.airline.controller;

import com.bootcamp.airline.controller.request.RouteRequest;
import com.bootcamp.airline.controller.response.PlaneResponse;
import com.bootcamp.airline.controller.response.RouteResponse;
import com.bootcamp.airline.model.Route;
import com.bootcamp.airline.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    public List<RouteResponse> routeResponses(List<Route> routes) {
        List<RouteResponse> routeResponses = new ArrayList<>();
        for (Route route : routes) {
            routeResponses.add(route.routeResponse());
        }
        return routeResponses;
    }

    @GetMapping("/routes")
    public List<RouteResponse> getRoutes() {
        return this.routeResponses(routeService.findAll());
    }

    @GetMapping("/routes/{id}")
    public RouteResponse getRouteById(@PathVariable(value = "id") Long id) {
        return routeService.findById(id).routeResponse();
    }

    @PostMapping(value = "/routes", consumes = "application/json", produces = "application/json")
    public RouteResponse createRoute(@RequestBody RouteRequest routeRequest) {
        return routeService.save(routeRequest.routeBuild(), routeRequest.getAirportId(),routeRequest.getPlaneId()).routeResponse();
    }

    @PutMapping(value = "/routes-update")
    public RouteResponse updateRoute(@RequestBody Long id, RouteRequest routeRequest) {
        return routeService.update(routeRequest.routeUp(id)).routeResponse();
    }

    @DeleteMapping(value = "/routes-delete")
    public void deleteRoute(@RequestBody Long id) {
        routeService.deleteById(id);
    }
}
