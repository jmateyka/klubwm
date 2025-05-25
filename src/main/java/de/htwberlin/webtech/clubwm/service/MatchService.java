package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.MatchEntity;
import de.htwberlin.webtech.clubwm.persistence.MatchRepository;
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
    private final GroupService groupService;

    @Autowired
    public MatchService(MatchRepository matchRepository, GroupService groupService) {
        this.matchRepository = matchRepository;
        this.groupService = groupService;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(this::convertEntityToApi)
                .collect(Collectors.toList());
    }

    public boolean updateMatches(List<Match> matches) {
        try {
            List<MatchEntity> matchEntities = matches.stream()
                    .map(match -> {
                        MatchEntity matchEntity = matchRepository.findById(match.getId())
                                .orElseThrow(() -> new IllegalArgumentException("Match not found with id: " + match.getId()));
                        matchEntity.setHomeScore(match.getHomeScore());
                        matchEntity.setVisitorScore(match.getVisitorScore());
                        return matchEntity;
                    })
                    .collect(Collectors.toList());

            matchRepository.saveAll(matchEntities);
            groupService.updateGroupStandings(matchEntities);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Match updateMatch(Long id, Match match) {
        MatchEntity matchEntity = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match not found with id: " + id));

        matchEntity.setHomeScore(match.getHomeScore());
        matchEntity.setVisitorScore(match.getVisitorScore());

        matchRepository.save(matchEntity);
        groupService.updateGroupStandings(List.of(matchEntity));

        return convertEntityToApi(matchEntity);
    }

    public boolean existsById(Long id) {
        return matchRepository.existsById(id);
    }

    private Match convertEntityToApi(MatchEntity matchEntity) {
        Team homeTeam = new Team(
                matchEntity.getHomeTeam().getId(),
                matchEntity.getHomeTeam().getName(),
                matchEntity.getHomeTeam().getCountry(),
                matchEntity.getHomeTeam().getLocation(),
                matchEntity.getHomeTeam().getLeague(),
                matchEntity.getHomeTeam().getAverageAge(),
                matchEntity.getHomeTeam().getMarketValue()
        );

        Team visitorTeam = new Team(
                matchEntity.getVisitorTeam().getId(),
                matchEntity.getVisitorTeam().getName(),
                matchEntity.getVisitorTeam().getCountry(),
                matchEntity.getVisitorTeam().getLocation(),
                matchEntity.getVisitorTeam().getLeague(),
                matchEntity.getVisitorTeam().getAverageAge(),
                matchEntity.getVisitorTeam().getMarketValue()
        );

        Stadium stadium = new Stadium(
                matchEntity.getStadium().getId(),
                matchEntity.getStadium().getName(),
                matchEntity.getStadium().getLocation(),
                matchEntity.getStadium().getCapacity()
        );

        return new Match(
                matchEntity.getId(),
                homeTeam,
                visitorTeam,
                matchEntity.getHomeScore(),
                matchEntity.getVisitorScore(),
                stadium
        );
    }
}