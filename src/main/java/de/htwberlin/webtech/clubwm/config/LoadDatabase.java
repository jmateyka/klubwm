package de.htwberlin.webtech.clubwm.config;

import de.htwberlin.webtech.clubwm.persistence.MatchEntity;
import de.htwberlin.webtech.clubwm.persistence.MatchRepository;
import de.htwberlin.webtech.clubwm.persistence.StadiumEntity;
import de.htwberlin.webtech.clubwm.persistence.StadiumRepository;
import de.htwberlin.webtech.clubwm.persistence.TeamEntity;
import de.htwberlin.webtech.clubwm.persistence.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TeamRepository teamRepository, StadiumRepository stadiumRepository, MatchRepository matchRepository) {
        return args -> {
            loadDataFromDatabase(teamRepository, stadiumRepository, matchRepository);
        };
    }

    @Transactional
    public void loadDataFromDatabase(TeamRepository teamRepository, StadiumRepository stadiumRepository, MatchRepository matchRepository) {
        // Lade Teams aus der Datenbank
        List<TeamEntity> allTeams = teamRepository.findAll();
        allTeams.forEach(team -> log.info("Found team in database: {}", team));

        // Lade Stadien aus der Datenbank
        List<StadiumEntity> allStadiums = stadiumRepository.findAll();
        allStadiums.forEach(stadium -> log.info("Found stadium in database: {}", stadium));

        // Lade Matches aus der Datenbank
        List<MatchEntity> allMatches = matchRepository.findAll();
        allMatches.forEach(match -> log.info("Found match in database: {}", match));

    }
}