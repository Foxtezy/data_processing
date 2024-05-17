package ru.nsu.makhov.airflights.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.makhov.airflights.api.ApiPaths;
import ru.nsu.makhov.airflights.api.dto.BookingDto;
import ru.nsu.makhov.airflights.api.dto.BookingResultDto;
import ru.nsu.makhov.airflights.core.entity.Flight;
import ru.nsu.makhov.airflights.core.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;

    @Operation(summary = "Create a booking for a selected route for a single passenger")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Invalid flight IDs"),
            @ApiResponse(responseCode = "409", description = "No free seats")
    })
    @PostMapping(ApiPaths.BOOKING)
    @Transactional
    public BookingResultDto createBooking(@RequestBody BookingDto bookingDto){
        List<Flight> flights = bookingDto.flightIds().stream().map(bookingRepository::getFlightById).map(f -> f.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find flight"))).toList();
        throw new UnsupportedOperationException();
    }
}
