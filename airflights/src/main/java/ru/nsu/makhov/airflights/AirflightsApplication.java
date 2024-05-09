package ru.nsu.makhov.airflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AirflightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirflightsApplication.class, args);
	}

}
