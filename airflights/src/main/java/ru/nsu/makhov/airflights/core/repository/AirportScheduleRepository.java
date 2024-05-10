package ru.nsu.makhov.airflights.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import ru.nsu.makhov.airflights.api.dto.ScheduleEntryDto;

import java.util.List;

@RegisterConstructorMapper(ScheduleEntryDto.class)
public interface AirportScheduleRepository {

    @SqlQuery("""
            SELECT DISTINCT rt.days_of_week, fv.scheduled_arrival::time as time, rt.departure_airport as other_airport, rt.flight_no
            FROM bookings.routes as rt
            JOIN bookings.flights_v fv on rt.flight_no = fv.flight_no
            WHERE rt.arrival_airport = :airport_code
            """)
    List<ScheduleEntryDto> getInboundSchedule(@Bind("airport_code") String airportCode);

    @SqlQuery("""
            SELECT DISTINCT rt.days_of_week, fv.scheduled_departure::time as time, rt.arrival_airport as other_airport, rt.flight_no
            FROM bookings.routes as rt
            JOIN bookings.flights_v fv on rt.flight_no = fv.flight_no
            WHERE rt.departure_airport = :airport_code
            """)
    List<ScheduleEntryDto> getOutboundSchedule(@Bind("airport_code") String airportCode);
}
