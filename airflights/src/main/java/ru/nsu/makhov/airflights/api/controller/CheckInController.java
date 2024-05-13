package ru.nsu.makhov.airflights.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.makhov.airflights.api.ApiPaths;
import ru.nsu.makhov.airflights.api.dto.BoardingPassDto;
import ru.nsu.makhov.airflights.api.dto.CheckInDto;
import ru.nsu.makhov.airflights.core.entity.BookingIds;
import ru.nsu.makhov.airflights.core.repository.CheckinRepository;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class CheckInController {

    private final CheckinRepository checkinRepository;

    @Operation(summary = "Online check-in for a flight")
    @ApiResponses({
            @ApiResponse(description = "Seat number", responseCode = "200"),
            @ApiResponse(description = "Wrong booking id or seat", responseCode = "404")
    })
    @PostMapping(ApiPaths.CHECKIN)
    public BoardingPassDto checkin(@RequestBody CheckInDto checkInDto) {
        BookingIds ids = checkinRepository.getBookingIdsByBookingAndFlightId(checkInDto.bookingId(), checkInDto.flightId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));
        List<String> seats = checkinRepository.getAllSeatsByAircraftCodeAndFareConditions(ids.aircraftCode(), ids.fareConditions());
        List<String> takenSeats = checkinRepository.getTakenSeats(checkInDto.flightId());
        seats.removeAll(takenSeats);
        if (seats.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Overbooking!");
        }
        Random rand = new Random();
        try {
            return checkinRepository.saveBoardingPass(new BoardingPassDto(checkInDto.flightId(), ids.ticketNo(), rand.nextInt(), seats.get(0)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already checkin");
        }
    }
}
