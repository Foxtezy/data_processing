package ru.nsu.makhov.airflights.api.dto;

public record BoardingPassDto(
        int flightId,
        String ticketNo,
        int boardingNo,
        String seatNo
) {
}
