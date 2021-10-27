package com.bootcamp.airline.controller.response;

import com.bootcamp.airline.model.Plane;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportResponse {
    private Long id;
    private String name;
    private List<RouteResponse> routeList = new ArrayList<>();
}

