package com.bootcamp.airline.controller;

import com.bootcamp.airline.controller.request.PlaneRequest;
import com.bootcamp.airline.controller.request.RouteRequest;
import com.bootcamp.airline.controller.response.PlaneResponse;
import com.bootcamp.airline.model.Plane;
import com.bootcamp.airline.service.PlaneService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    public List<PlaneResponse> planeResponses(List<Plane> planes) {
        List<PlaneResponse> planeResponses = new ArrayList<>();
        for (Plane plane : planes) {
            planeResponses.add(plane.planeResponse());
        }
        return planeResponses;
    }

    @GetMapping("/planes")
    public List<PlaneResponse> getPlanes() {
        return this.planeResponses(planeService.findAll());
    }

    @GetMapping("/planes/{id}")
    public PlaneResponse getPlaneById(@PathVariable(value = "id") Long id) {
        return planeService.findById(id).planeResponse();
    }

    @PostMapping(value = "/planes", consumes = "application/json", produces = "application/json")
    public PlaneResponse createPlane(@RequestBody PlaneRequest planeRequest) {
        return planeService.save(planeRequest.planeBuild(), planeRequest.getAirportId()).planeResponse();
    }

    @PutMapping(value = "/planes-update")
    public PlaneResponse updatePlane(@RequestParam Long id, PlaneRequest planeRequest) {
        return planeService.update(planeRequest.planeUp(id), planeRequest.getAirportId()).planeResponse();
    }

    @PutMapping(value = "/routes/{routeId}/plane/{planeId}")
    public PlaneResponse addRouteToPlane(@PathVariable Long routeId, Long planeId) {
        return planeService.addRouteToPlane(routeId, planeId).planeResponse();
    }

    @DeleteMapping(value = "/planes-delete")
    public void deletePet(@RequestParam Long id) {
        planeService.deleteById(id);
    }
}