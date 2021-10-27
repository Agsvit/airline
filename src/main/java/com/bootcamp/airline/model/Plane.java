package com.bootcamp.airline.model;

import com.bootcamp.airline.controller.response.PlaneResponse;
import com.bootcamp.airline.controller.response.RouteResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private int maxRange;


    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport = new Airport();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "route_plane",
            joinColumns = @JoinColumn(name = "plane_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"))
    List<Route> routes = new ArrayList<Route>();

    @JsonIgnore
    public PlaneResponse planeResponse() {
        List<RouteResponse> routeResponses = new ArrayList<RouteResponse>();
        if (this.routes!=null && !this.routes.isEmpty()) {
            for(Route route : this.routes) {
                routeResponses.add(new RouteResponse(
                        route.getId(),
                        route.getDestiny(),
                        route.getDistance()
                ));
            }
        }
        return new PlaneResponse(
                this.getId(),
                this.getModel(),
                this.getMaxRange(),
                this.airport.getName(),
                routeResponses);
    }
}