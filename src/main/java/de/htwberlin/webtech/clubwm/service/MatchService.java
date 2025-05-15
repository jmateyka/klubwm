package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.MatchEntity;
import de.htwberlin.webtech.clubwm.persistence.MatchRepository;
import de.htwberlin.webtech.clubwm.persistence.TeamEntity;
import de.htwberlin.webtech.clubwm.persistence.StadiumEntity;
import de.htwberlin.webtech.clubwm.web.api.Match;
import de.htwberlin.webtech.clubwm.web.api.Stadium;
import de.htwberlin.webtech.clubwm.web.api.Team;
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
        return matchRepository.findAll().stream().map(this::convertEntityToApi).collect(Collectors.toList());
    }

    private Match convertEntityToApi(MatchEntity matchEntity) {
        Team homeTeam = new Team(matchEntity.getHomeTeam().getId(), matchEntity.getHomeTeam().getName());
        Team visitorTeam = new Team(matchEntity.getVisitorTeam().getId(), matchEntity.getVisitorTeam().getName());
        Stadium stadium = new Stadium(matchEntity.getStadium().getId(), matchEntity.getStadium().getName(),
                matchEntity.getStadium().getLocation(), matchEntity.getStadium().getCapacity());

        return new Match(matchEntity.getId(), homeTeam, visitorTeam, matchEntity.getHomeScore(), matchEntity.getVisitorScore(), stadium);
    }

    public Match saveMatch(Match match) {
        MatchEntity matchEntity = new MatchEntity(match.getId(),
                new TeamEntity(match.getHomeTeam().getId(), match.getHomeTeam().getName()),
                new TeamEntity(match.getVisitorTeam().getId(), match.getVisitorTeam().getName()),
                match.getHomeScore(), match.getVisitorScore(),
                new StadiumEntity(match.getStadium().getId(), match.getStadium().getName(), match.getStadium().getLocation(), match.getStadium().getCapacity()));

        MatchEntity savedMatchEntity = matchRepository.save(matchEntity);
        return convertEntityToApi(savedMatchEntity);
    }
}