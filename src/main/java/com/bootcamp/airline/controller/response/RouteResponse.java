package com.bootcamp.airline.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {
        private Long id;
        private String destiny;
        private int distance;
}
