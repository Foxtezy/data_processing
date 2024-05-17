package ru.nsu.makhov.airflights.core.entity;

import java.time.OffsetDateTime;

public record Flight (

    int flightId,
    String flightNo,
    OffsetDateTime scheduledDeparture,
    OffsetDateTime scheduledArrival,
    String departureAirport,

    String arrivalAirport,
    String status,
    String aircraftCode,
    OffsetDateTime actualDeparture,
    OffsetDateTime actualArrival

){
}
