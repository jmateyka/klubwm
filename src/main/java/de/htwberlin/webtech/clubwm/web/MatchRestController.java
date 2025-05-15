package de.htwberlin.webtech.clubwm.web;

import de.htwberlin.webtech.clubwm.web.api.Match;
import de.htwberlin.webtech.clubwm.web.api.Stadium;
import de.htwberlin.webtech.clubwm.web.api.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "https://klubwm-frontend.onrender.com")
@RestController
@RequestMapping("/api/v1")
public class MatchRestController {

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> fetchMatches() {
        // Feste Beispiel-Daten
        Team homeTeam = new Team(1, "Real Madrid");
        Team visitorTeam = new Team(2, "FC Bayern München");
        Stadium stadium = new Stadium(1, "Allianz Arena", "München", 75000);

        Match match = new Match(1, homeTeam, visitorTeam, 0, 0, stadium);

        // Ein Match in ein List-Objekt verpacken
        List<Match> matches = Collections.singletonList(match);

        return ResponseEntity.ok(matches);
    }
}