package ru.nsu.makhov.airflights.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import ru.nsu.makhov.airflights.core.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    @SqlQuery("""
            SELECT *
            FROM bookings.flights as f
            WHERE f.flight_id = :flight_id
            """)
    @RegisterConstructorMapper(Flight.class)
    Optional<Flight> getFlightById(@Bind("flight_id") int flightId);
}
