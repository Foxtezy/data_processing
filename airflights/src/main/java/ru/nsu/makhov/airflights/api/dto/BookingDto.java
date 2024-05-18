package ru.nsu.makhov.airflights.api.dto;

import java.util.List;

public record BookingDto (
    List<Integer> flightIds,
    String passenger,
    BookingClass bookingClass,
    Boolean extraSpace
) {
}
