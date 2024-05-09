package ru.nsu.makhov.airflights.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.makhov.airflights.api.ApiPaths;
import ru.nsu.makhov.airflights.api.dto.BookingDto;
import ru.nsu.makhov.airflights.api.dto.BookingResultDto;

@RestController
@RequiredArgsConstructor
public class BookingController {

    @Operation(summary = "Create a booking for a selected route for a single passenger")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Invalid flight IDs"),
            @ApiResponse(responseCode = "409", description = "No free seats")
    })
    @PostMapping(ApiPaths.BOOKING)
    public BookingResultDto createBooking(@RequestBody BookingDto bookingDto){
        throw new UnsupportedOperationException();
    }
}
