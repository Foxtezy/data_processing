package ru.nsu.makhov.airflights.api.dto;

import java.time.LocalTime;
import java.util.List;

public record ScheduleEntryDto (
        List<Integer> daysOfWeek,
        LocalTime time,
        String otherAirport,
        String flightNo
){
}
