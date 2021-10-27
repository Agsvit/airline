package com.bootcamp.airline.controller.request;

import com.bootcamp.airline.model.Airport;
import com.bootcamp.airline.model.Route;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteRequest {
    private String destiny;
    private int distance;
    private Long airportId;
    
    @JsonIgnore
    public Route routeBuild() {
        return Route.builder()
                .destiny(this.getDestiny())
                .distance(this.getDistance())
                .build();
    }

    @JsonIgnore
    public Route routeUp(Long id) {
        return Route.builder()
                .id(id)
                .destiny(this.getDestiny())
                .distance(this.getDistance())
                .build();
    }

}