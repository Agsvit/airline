package com.bootcamp.airline.controller.request;

import com.bootcamp.airline.model.Airport;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportRequest {
    private String name;

    @JsonIgnore
    public Airport airportBuild() {
        return Airport.builder()
                .name(this.getName())
                .planes(new ArrayList<>())
                .routes(new ArrayList<>())
                .build();
    }

    @JsonIgnore
    public Airport airportUp(Long id) {
        return Airport.builder()
                .id(id)
                .name(this.getName())
                .build();
    }
}