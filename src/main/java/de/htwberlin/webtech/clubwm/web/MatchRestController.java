package de.htwberlin.webtech.clubwm.web;

import de.htwberlin.webtech.clubwm.service.MatchService;
import de.htwberlin.webtech.clubwm.web.api.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "https://klubwm-frontend.onrender.com")
@RestController
@RequestMapping("/api/v1")
public class MatchRestController {

    private List<Match> matches;
    private final MatchService matchService;


    @Autowired
    public MatchRestController(MatchService matchService) {
        matches = new ArrayList<>();
        matches.add(new Match(01, "Germany", "Brazil", 7, 1));
        matches.add(new Match(02, "Argentina", "France", 3, 4));
        this.matchService = matchService;
    }

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> fetchMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @PostMapping("/matches")
    public ResponseEntity<Match> saveMatchResult(@RequestBody Match match) {
        Match savedMatch = matchService.saveMatch(match);  // Speichern des Matches
        return ResponseEntity.ok(savedMatch);  // Rückgabe des gespeicherten Matches
    }
}