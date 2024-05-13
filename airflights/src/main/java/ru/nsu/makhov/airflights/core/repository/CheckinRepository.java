package ru.nsu.makhov.airflights.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.nsu.makhov.airflights.api.dto.BoardingPassDto;
import ru.nsu.makhov.airflights.core.entity.BookingIds;

import java.util.List;
import java.util.Optional;

@RegisterConstructorMapper(BookingIds.class)
public interface CheckinRepository {

    @SqlQuery("""
            SELECT t.ticket_no, tf.fare_conditions, f.aircraft_code
            FROM bookings.tickets as t
            JOIN bookings.ticket_flights tf on t.ticket_no = tf.ticket_no
            JOIN bookings.flights f on f.flight_id = tf.flight_id
            WHERE tf.flight_id = :flight_id AND t.book_ref = :book_ref
            """)
    Optional<BookingIds> getBookingIdsByBookingAndFlightId(@Bind("book_ref") String bookingRef, @Bind("flight_id") int flightId);

    @SqlQuery("""
            SELECT s.seat_no
            FROM bookings.seats as s
            WHERE s.aircraft_code = :aircraft_code AND s.fare_conditions = :fare_conditions
            """)
    List<String> getAllSeatsByAircraftCodeAndFareConditions(@Bind("aircraft_code") String aircraftCode, @Bind("fare_conditions") String fareConditions);

    @SqlQuery("""
            SELECT bp.seat_no
            FROM bookings.boarding_passes as bp
            WHERE bp.flight_id = :flight_id
            """)
    List<String> getTakenSeats(@Bind("flight_id") int flightId);

    @SqlUpdate("""
            INSERT INTO bookings.boarding_passes (ticket_no, flight_id, boarding_no, seat_no)
            VALUES (:b.ticketNo, :b.flightId, :b.boardingNo, :b.seatNo)
            """)
    @GetGeneratedKeys
    @RegisterConstructorMapper(BoardingPassDto.class)
    BoardingPassDto saveBoardingPass(@BindMethods(value = "b") BoardingPassDto boardingPassDto);
}
