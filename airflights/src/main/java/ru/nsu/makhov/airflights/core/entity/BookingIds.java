package ru.nsu.makhov.airflights.core.entity;

public record BookingIds(
        String ticketNo,
        String fareConditions,
        String aircraftCode
) {
}
