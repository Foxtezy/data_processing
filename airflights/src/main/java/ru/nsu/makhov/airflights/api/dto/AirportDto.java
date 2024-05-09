package ru.nsu.makhov.airflights.api.dto;

public record AirportDto (

        String airportCode,
        String airportName,
        String city,
        double longitude,
        double latitude,
        String timezone
){
}
