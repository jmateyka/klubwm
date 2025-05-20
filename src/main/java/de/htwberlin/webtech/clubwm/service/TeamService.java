package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.TeamEntity;
import de.htwberlin.webtech.clubwm.persistence.TeamRepository;
import de.htwberlin.webtech.clubwm.web.api.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::convertEntityToApi)
                .collect(Collectors.toList());
    }

    private Team convertEntityToApi(TeamEntity entity) {
        return new Team(
                entity.getId(),
                entity.getName(),
                entity.getCountry(),
                entity.getLocation(),
                entity.getLeague(),
                entity.getAverageAge(),
                entity.getMarketValue()
        );
    }
}