package ru.nsu.makhov.airflights.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import ru.nsu.makhov.airflights.api.dto.AirportDto;
import ru.nsu.makhov.airflights.api.dto.CityDto;

import java.util.List;

public interface UtilsRepository {

    @RegisterConstructorMapper(CityDto.class)
    @SqlQuery("SELECT DISTINCT a.city ->> :locale as city FROM bookings.airports_data as a")
    List<CityDto> getAllCities(@Bind("locale") String locale);

    @RegisterConstructorMapper(AirportDto.class)
    @SqlQuery("SELECT a.airport_name ->> :locale as airport_name FROM bookings.airports_data as a")
    List<AirportDto> getAllAirports(@Bind("locale") String locale);

    @RegisterConstructorMapper(AirportDto.class)
    @SqlQuery("SELECT a.airport_name ->> :locale as airport_name FROM bookings.airports_data as a WHERE a.city ->> :locale = :city")
    List<AirportDto> getAllAirportsInCity(@Bind("city") String city, @Bind("locale") String locale);
}
