package com.bootcamp.airline.controller.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaneResponse {
    private Long id;
    private String model;
    private int maxRange;
    private String airportName;
    private List<RouteResponse> routeResponses;
}
