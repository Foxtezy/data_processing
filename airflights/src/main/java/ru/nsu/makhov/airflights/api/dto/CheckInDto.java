package ru.nsu.makhov.airflights.api.dto;

public record CheckInDto(
        String bookingId,
        int flightId
) {
}
