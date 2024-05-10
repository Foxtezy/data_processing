package ru.nsu.makhov.airflights.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import ru.nsu.makhov.airflights.api.dto.AirportDto;
import ru.nsu.makhov.airflights.api.dto.CityDto;

import java.util.List;

public interface UtilsRepository {

    @RegisterConstructorMapper(CityDto.class)
    @SqlQuery("SELECT DISTINCT a.city FROM bookings.airports as a")
    List<CityDto> getAllCities();

    @RegisterConstructorMapper(AirportDto.class)
    @SqlQuery("SELECT * FROM bookings.airports")
    List<AirportDto> getAllAirports();

    @RegisterConstructorMapper(AirportDto.class)
    @SqlQuery("SELECT * FROM bookings.airports WHERE city = :city")
    List<AirportDto> getAllAirportsInCity(@Bind("city") String city);
}
