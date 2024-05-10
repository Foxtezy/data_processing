package ru.nsu.makhov.airflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class AirflightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirflightsApplication.class, args);
	}

}
