package ru.nsu.makhov.airflights.api.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record ScheduleEntryDto (
        List<Integer> daysOfWeek,
        OffsetDateTime time,
        String otherAirport,
        String flightNo
){
}
