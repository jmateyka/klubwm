package de.htwberlin.webtech.clubwm;

import static org.assertj.core.api.Assertions.assertThat;

import de.htwberlin.webtech.clubwm.persistence.TeamEntity;
import de.htwberlin.webtech.clubwm.persistence.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        teamRepository.save(new TeamEntity("Test Team 1", "Country", "Location", "League", 25.0, "1 Mrd. €"));
        teamRepository.save(new TeamEntity("Test Team 2", "Country", "Location", "League", 27.0, "500 Mio. €"));
    }

    @Test
    void findAllTeams() {
        List<TeamEntity> teams = teamRepository.findAll();
        assertThat(teams).isNotNull();
        assertThat(teams.size()).isGreaterThan(0);
    }
}