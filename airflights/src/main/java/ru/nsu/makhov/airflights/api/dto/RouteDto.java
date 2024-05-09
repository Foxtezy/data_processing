package ru.nsu.makhov.airflights.api.dto;

import java.time.OffsetDateTime;

public record RouteDto(
        String departureAirport,
        String arrivalAirport,
        OffsetDateTime departureTime,
        OffsetDateTime arrivalTime,
        int flightId
){
}
