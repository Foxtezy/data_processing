package ru.nsu.makhov.airflights.core.entity;

public record Ticket(
        String bookRef,
        String ticketNo,
        String passengerId,
        String passengerName
) {
}
