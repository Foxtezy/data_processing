package ru.nsu.makhov.airflights.core.entity;

import java.time.OffsetDateTime;

public record Booking (
    String bookRef,
    OffsetDateTime bookDate,
    Integer totalAmount
) {
}
