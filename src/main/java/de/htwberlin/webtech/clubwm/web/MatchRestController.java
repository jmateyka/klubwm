package de.htwberlin.webtech.clubwm.web;

import de.htwberlin.webtech.clubwm.service.MatchService;
import de.htwberlin.webtech.clubwm.web.api.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://klubwm-frontend.onrender.com")
@RestController
@RequestMapping("/api/v1")
public class MatchRestController {

    private final MatchService matchService;

    @Autowired
    public MatchRestController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> fetchMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @PostMapping("/matches")
    public ResponseEntity<Match> saveMatchResult(@RequestBody Match match) {
        Match savedMatch = matchService.saveMatch(match);
        return ResponseEntity.ok(savedMatch);
    }
}