package ru.nsu.makhov.airflights.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.nsu.makhov.airflights.api.dto.BookingClass;
import ru.nsu.makhov.airflights.core.entity.Booking;
import ru.nsu.makhov.airflights.core.entity.Flight;
import ru.nsu.makhov.airflights.core.entity.Ticket;
import ru.nsu.makhov.airflights.core.entity.TicketFlight;

import java.util.Optional;

public interface BookingRepository {

    @SqlQuery("""
            SELECT *
            FROM bookings.flights as f
            WHERE f.flight_id = :flight_id
            """)
    @RegisterConstructorMapper(Flight.class)
    Optional<Flight> getFlightById(@Bind("flight_id") int flightId);

    @SqlQuery("""
            SELECT p.amount
            FROM bookings.flights as f
            JOIN bookings.prices as p ON f.flight_no = p.flight_no
            WHERE f.flight_id = :flight_id AND p.fare_conditions = :fare_conditions
            AND p.extra_space = COALESCE(:extra_space, false)
            """)
    Optional<Integer> getPriceByFlightId(@Bind("flight_id") int flightId,
                                         @Bind("fare_conditions") BookingClass fareConditions,
                                         @Bind("extra_space") Boolean extraSpace
    );

    @SqlUpdate("""
            INSERT INTO bookings.bookings (book_ref, book_date, total_amount)
            VALUES (:b.bookRef, :b.bookDate, :b.totalAmount)
            """)
    @RegisterConstructorMapper(Booking.class)
    void saveBooking(@BindMethods(value = "b") Booking booking);

    @SqlUpdate("""
            INSERT INTO bookings.tickets (ticket_no, book_ref, passenger_id, passenger_name, contact_data)
            VALUES (:t.ticketNo, :t.bookRef, :t.passengerId, :t.passengerName, null)
            """)
    @RegisterConstructorMapper(Ticket.class)
    void saveTicket(@BindMethods(value = "t") Ticket ticket);

    @SqlUpdate("""
            INSERT INTO bookings.ticket_flights (ticket_no, flight_id, fare_conditions, amount)
            VALUES (:tf.ticketNo, :tf.flightId, :tf.fareConditions, :tf.amount)
            """)
    @RegisterConstructorMapper(TicketFlight.class)
    void saveTicketFlight(@BindMethods(value = "tf") TicketFlight ticketFlight);
}
