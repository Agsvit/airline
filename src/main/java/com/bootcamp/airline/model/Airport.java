package com.bootcamp.airline.model;

import com.bootcamp.airline.controller.response.AirportResponse;
import com.bootcamp.airline.controller.response.PlaneResponse;
import com.bootcamp.airline.controller.response.RouteResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL)
    private List<Route> routes;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL)
    private List<Plane> planes;

    @JsonIgnore
    public AirportResponse airportResponse() {
        List<RouteResponse> routeResponses = new ArrayList<>();
        if (this.routes!=null && !this.routes.isEmpty()) {
            for(Route route : this.routes) {
                routeResponses.add(new RouteResponse(
                        route.getId(),
                        route.getDestiny(),
                        route.getDistance()
                        ));
            }
        }
        return new AirportResponse(
                this.getId(),
                this.getName(),
                routeResponses);
    }
}
