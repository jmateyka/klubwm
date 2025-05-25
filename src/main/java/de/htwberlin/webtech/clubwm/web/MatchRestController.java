package de.htwberlin.webtech.clubwm.web;

import de.htwberlin.webtech.clubwm.service.MatchService;
import de.htwberlin.webtech.clubwm.web.api.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://klubwm-frontend.onrender.com")
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

    @PutMapping("/matches/batch")
    public ResponseEntity<Void> updateMatches(@RequestBody List<Match> matches) {
        boolean allUpdated = matchService.updateMatches(matches);
        if (allUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}