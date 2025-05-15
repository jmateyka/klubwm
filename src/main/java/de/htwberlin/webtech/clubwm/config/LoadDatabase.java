package de.htwberlin.webtech.clubwm.config;
/*
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

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TeamRepository teamRepository, StadiumRepository stadiumRepository, MatchRepository matchRepository) {
        return args -> {
            saveInitialData(teamRepository, stadiumRepository, matchRepository);
        };
    }

    @Transactional
    public void saveInitialData(TeamRepository teamRepository, StadiumRepository stadiumRepository, MatchRepository matchRepository) {
        // Beispielteams
        TeamEntity realMadrid = new TeamEntity("Real Madrid", "Spanien", "Madrid", "La Liga", 27.1, "1,27 Mrd. €");
        TeamEntity bayernMunich = new TeamEntity("FC Bayern München", "Deutschland", "München", "Bundesliga", 27.8, "859,00 Mio. €");

        // Speichern der Teams in der Datenbank
        realMadrid = teamRepository.save(realMadrid);
        log.info("Saved team entity: {}", realMadrid);

        bayernMunich = teamRepository.save(bayernMunich);
        log.info("Saved team entity: {}", bayernMunich);

        // Beispielstadion
        StadiumEntity stadium = new StadiumEntity("Allianz Arena", "München", 75000);
        stadium = stadiumRepository.save(stadium);
        log.info("Saved stadium entity: {}", stadium);

        // Beispielmatch Real Madrid gegen FC Bayern München
        MatchEntity match = new MatchEntity(realMadrid, bayernMunich, 0, 0, stadium);
        matchRepository.save(match);
        log.info("Saved match entity: {}", match);
    }
}

 */