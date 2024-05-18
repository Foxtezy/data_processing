package ru.nsu.makhov.airflights.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.makhov.airflights.api.ApiPaths;
import ru.nsu.makhov.airflights.api.dto.BookingDto;
import ru.nsu.makhov.airflights.api.dto.BookingResultDto;
import ru.nsu.makhov.airflights.core.entity.Booking;
import ru.nsu.makhov.airflights.core.entity.Flight;
import ru.nsu.makhov.airflights.core.entity.Ticket;
import ru.nsu.makhov.airflights.core.entity.TicketFlight;
import ru.nsu.makhov.airflights.core.repository.BookingRepository;
import ru.nsu.makhov.airflights.core.repository.CheckinRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;

    private final CheckinRepository checkinRepository;

    @Operation(summary = "Create a booking for a selected route for a single passenger")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Invalid flight IDs"),
            @ApiResponse(responseCode = "409", description = "No free seats")
    })
    @PostMapping(ApiPaths.BOOKING)
    @Transactional
    public BookingResultDto createBooking(@RequestBody BookingDto bookingDto){
        List<Flight> flights = bookingDto.flightIds().stream().map(bookingRepository::getFlightById)
                .map(f -> f.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find flight"))).toList();
        List<Integer> prices = bookingDto.flightIds().stream()
                .map(id -> bookingRepository.getPriceByFlightId(id, bookingDto.bookingClass(), bookingDto.extraSpace()))
                .map(f -> f.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find flight"))).toList();

        Booking booking = new Booking(RandomStringUtils.randomAlphanumeric(6), OffsetDateTime.now(), prices.stream().reduce(Integer::sum).orElseThrow());
        bookingRepository.saveBooking(booking);

        Ticket ticket = new Ticket(booking.bookRef(), RandomStringUtils.randomAlphanumeric(12), bookingDto.passenger(), bookingDto.passenger());
        bookingRepository.saveTicket(ticket);

        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            Integer price = prices.get(i);
            List<String> seats = checkinRepository.getAllSeatsByAircraftCodeAndFareConditions(flight.aircraftCode(), bookingDto.bookingClass().name());
            List<String> takenSeats = checkinRepository.getTakenSeats(flight.flightId());
            seats.removeAll(takenSeats);
            if (seats.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "No seats for flight: " + flight.flightId());
            }
            bookingRepository.saveTicketFlight(new TicketFlight(ticket.ticketNo(), flight.flightId(), bookingDto.bookingClass().name(), price));
        }

        return new BookingResultDto(booking.totalAmount(), booking.bookRef(), ticket.ticketNo());
    }
}
