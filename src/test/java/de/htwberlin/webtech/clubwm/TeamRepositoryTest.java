package de.htwberlin.webtech.clubwm;

import static org.assertj.core.api.Assertions.assertThat;

import de.htwberlin.webtech.clubwm.persistence.TeamEntity;
import de.htwberlin.webtech.clubwm.persistence.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void findAllTeams() {
        List<TeamEntity> teams = teamRepository.findAll();
        assertThat(teams).isNotNull();
        assertThat(teams.size()).isGreaterThan(0);
    }
}