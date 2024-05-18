package ru.nsu.makhov.airflights.core.entity;

public record TicketFlight(
        String ticketNo,
        int flightId,
        String fareConditions,
        Integer amount
) {
}
