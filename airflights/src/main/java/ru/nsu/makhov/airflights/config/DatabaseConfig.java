package ru.nsu.makhov.airflights.config;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.makhov.airflights.core.repository.AirportScheduleRepository;
import ru.nsu.makhov.airflights.core.repository.BookingRepository;
import ru.nsu.makhov.airflights.core.repository.CheckinRepository;
import ru.nsu.makhov.airflights.core.repository.UtilsRepository;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        return Jdbi.create(dataSource)
                .installPlugin(new SqlObjectPlugin())
                .installPlugin(new PostgresPlugin());
    }

    @Bean
    public UtilsRepository utilsRepository(Jdbi jdbi) {
        return jdbi.onDemand(UtilsRepository.class);
    }

    @Bean
    public AirportScheduleRepository scheduleRepository(Jdbi jdbi) {
        return jdbi.onDemand(AirportScheduleRepository.class);
    }

    @Bean
    public CheckinRepository checkinRepository(Jdbi jdbi) {
        return jdbi.onDemand(CheckinRepository.class);
    }

    @Bean
    public BookingRepository bookingRepository(Jdbi jdbi) {
        return jdbi.onDemand(BookingRepository.class);
    }
}
