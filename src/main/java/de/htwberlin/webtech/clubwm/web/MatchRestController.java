package de.htwberlin.webtech.clubwm.web;

import de.htwberlin.webtech.clubwm.web.api.Match;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "https://klubwm-frontend.onrender.com")
@RestController
public class MatchRestController {

    private List matches;

    public MatchRestController() {
        matches = new ArrayList<>();
        matches.add(new Match(01, "Germany", "Brazil", 7, 1));
        matches.add(new Match(02, "Argentina", "France", 3, 4));
    }

    @GetMapping(path = "/api/v1/matches")
    public ResponseEntity<List<Match>> fetchMatches() {
        return ResponseEntity.ok(matches);
    }


}
