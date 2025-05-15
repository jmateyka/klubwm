package de.htwberlin.webtech.clubwm.config;

import de.htwberlin.webtech.clubwm.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(TeamRepository teamRepository, StadiumRepository stadiumRepository, MatchRepository matchRepository) {
        return args -> {
            // Teams
            TeamEntity realMadrid = new TeamEntity("Real Madrid", "Spanien", "Madrid", "La Liga", 27.1, "1,27 Mrd. €");
            TeamEntity bayernMunich = new TeamEntity("FC Bayern München", "Deutschland", "München", "Bundesliga", 27.8, "859,00 Mio. €");
            // ... (weitere Teams hinzufügen)

            // Teams speichern
            teamRepository.save(realMadrid);
            teamRepository.save(bayernMunich);
            // ... (weitere Teams speichern)

            // Stadium
            StadiumEntity stadium = new StadiumEntity(1, "Allianz Arena", "München", 75000);
            stadiumRepository.save(stadium);

            // Match
            MatchEntity match = new MatchEntity(1, realMadrid, bayernMunich, 0, 0, stadium);
            matchRepository.save(match);
        };
    }
}