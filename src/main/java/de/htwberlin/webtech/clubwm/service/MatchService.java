package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.MatchEntity;
import de.htwberlin.webtech.clubwm.persistence.MatchRepository;
import de.htwberlin.webtech.clubwm.persistence.TeamEntity;
import de.htwberlin.webtech.clubwm.persistence.TeamRepository;
import de.htwberlin.webtech.clubwm.persistence.StadiumEntity;
import de.htwberlin.webtech.clubwm.persistence.StadiumRepository;
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
    private final TeamRepository teamRepository;
    private final StadiumRepository stadiumRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, StadiumRepository stadiumRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.stadiumRepository = stadiumRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(this::convertEntityToApi)
                .collect(Collectors.toList());
    }

    public Match saveMatch(Match match) {
        TeamEntity homeTeamEntity = teamRepository.findById(match.getHomeTeam().getId())
                .orElseThrow(() -> new IllegalArgumentException("Home Team not found"));
        TeamEntity visitorTeamEntity = teamRepository.findById(match.getVisitorTeam().getId())
                .orElseThrow(() -> new IllegalArgumentException("Visitor Team not found"));

        StadiumEntity stadiumEntity = stadiumRepository.findById(match.getStadium().getId())
                .orElseThrow(() -> new IllegalArgumentException("Stadium not found"));

        MatchEntity matchEntity = new MatchEntity(homeTeamEntity, visitorTeamEntity,
                match.getHomeScore(), match.getVisitorScore(), stadiumEntity);

        MatchEntity savedMatchEntity = matchRepository.save(matchEntity);
        return convertEntityToApi(savedMatchEntity);
    }

    private Match convertEntityToApi(MatchEntity matchEntity) {
        Team homeTeam = new Team(matchEntity.getHomeTeam().getId(), matchEntity.getHomeTeam().getName());
        Team visitorTeam = new Team(matchEntity.getVisitorTeam().getId(), matchEntity.getVisitorTeam().getName());
        Stadium stadium = new Stadium(matchEntity.getStadium().getId(), matchEntity.getStadium().getName(),
                matchEntity.getStadium().getLocation(), matchEntity.getStadium().getCapacity());

        return new Match(matchEntity.getId(), homeTeam, visitorTeam,
                matchEntity.getHomeScore(), matchEntity.getVisitorScore(), stadium);
    }
}