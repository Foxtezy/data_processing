package ru.nsu.makhov.prices;

public record FlightPrice(
        String flightNo,
        String fareConditions,
        int minAmount,
        int maxAmount
) {
}
