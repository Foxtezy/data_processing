package ru.nsu.makhov.airflights.api.dto;

public record BookingResultDto(
        double price,
        String bookingRef,

        String ticketNo
) {
}
