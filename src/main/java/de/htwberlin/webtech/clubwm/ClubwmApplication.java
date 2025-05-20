package de.htwberlin.webtech.clubwm;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClubwmApplication {

	public static void main(String[] args) {
		// Abrufen der Umgebungsvariablen
		String url = System.getenv("DATASOURCE_URL");
		String username = System.getenv("DATASOURCE_USERNAME");
		String password = System.getenv("DATASOURCE_PASSWORD");

		Flyway flyway = Flyway.configure()
				.dataSource(url, username, password)
				.load();

		// Repariere die Checksummen in der Flyway-Historie
		flyway.repair();

		SpringApplication.run(ClubwmApplication.class, args);
	}
}