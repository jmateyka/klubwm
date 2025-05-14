package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.MatchEntity;
import de.htwberlin.webtech.clubwm.persistence.MatchRepository;
import de.htwberlin.webtech.clubwm.web.api.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(this::transformEntityToApi)
                .collect(Collectors.toList());
    }

    public Match saveMatch(Match match) {
        MatchEntity entity = matchRepository.save(transformApiToEntity(match));
        return transformEntityToApi(entity);
    }

    public Match transformEntityToApi(MatchEntity entity) {
        return new Match(
                entity.getId(),
                entity.getHomeTeam(),
                entity.getVisitorTeam(),
                entity.getHomeScore(),
                entity.getVisitorScore()
        );
    }

    public MatchEntity transformApiToEntity(Match match) {
        return new MatchEntity(
                match.getId(),
                match.getHomeTeam(),
                match.getVisitorTeam(),
                match.getHomeScore(),
                match.getVisitorScore()
        );
    }
}