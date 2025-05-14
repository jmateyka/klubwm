package de.htwberlin.webtech.clubwm.web;

import de.htwberlin.webtech.clubwm.web.api.Match;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "https://klubwm-frontend.onrender.com")
@RestController
@RequestMapping("/api/v1")
public class MatchRestController {

    private List<Match> matches;

    public MatchRestController() {
        matches = new ArrayList<>();
        matches.add(new Match(01, "Germany", "Brazil", 7, 1));
        matches.add(new Match(02, "Argentina", "France", 3, 4));
    }

    @GetMapping(path = "/api/v1/matches")
    public ResponseEntity<List<Match>> fetchMatches() {
        return ResponseEntity.ok(matches);
    }


    @PostMapping("/matches")
    public ResponseEntity<Void> saveMatchResult(@RequestBody Match match) {
        // Existierendes Spiel anhand der ID finden und aktualisieren
        matches.removeIf(m -> m.getId() == match.getId());
        matches.add(match);
        return ResponseEntity.ok().build();
    }

}
