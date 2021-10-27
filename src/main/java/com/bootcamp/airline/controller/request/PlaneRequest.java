package com.bootcamp.airline.controller.request;

import com.bootcamp.airline.model.Airport;
import com.bootcamp.airline.model.Plane;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaneRequest {
    private String model;
    private  int range;
    private Long airportId;

    @JsonIgnore
    public Plane planeBuild() {
        return Plane.builder()
                .model(this.getModel())
                .maxRange(this.getRange())
                .routes(new ArrayList<>())
                .airport(new Airport())
                .build();
    }

    @JsonIgnore
    public Plane planeUp(Long id) {
        return Plane.builder()
                .id(id)
                .model(this.getModel())
                .maxRange(this.getRange())
                .build();
    }
}