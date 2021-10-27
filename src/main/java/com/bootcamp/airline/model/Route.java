package com.bootcamp.airline.model;

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
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String destiny;
    private int distance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @ManyToMany(mappedBy = "routes", cascade = CascadeType.ALL)
    List<Plane> planes = new ArrayList<Plane>();

    @JsonIgnore
    public RouteResponse routeResponse() {
        return new RouteResponse(
                this.getId(),
                this.getDestiny(),
                this.getDistance());
    }
}
