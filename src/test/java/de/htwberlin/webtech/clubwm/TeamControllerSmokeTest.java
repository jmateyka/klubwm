package de.htwberlin.webtech.clubwm;

import static org.assertj.core.api.Assertions.assertThat;

import de.htwberlin.webtech.clubwm.web.TeamRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeamControllerSmokeTest {

    @Autowired
    private TeamRestController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}