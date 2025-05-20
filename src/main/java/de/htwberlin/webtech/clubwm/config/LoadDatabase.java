package de.htwberlin.webtech.clubwm.config;

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
    CommandLineRunner initDatabase(TeamRepository teamRepository) {
        return args -> {
            loadDataAndVerify(teamRepository);
        };
    }

    @Transactional
    public void loadDataAndVerify(TeamRepository teamRepository) {
        // Prüfen und anzeigen der vorhandenen Teams
        List<TeamEntity> teams = teamRepository.findAll();
        log.info("There are {} teams in the database:", teams.size());
        teams.forEach(team -> log.info("Found team in database: {}", team));
    }
}