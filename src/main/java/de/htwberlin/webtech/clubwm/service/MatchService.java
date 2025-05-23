package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.*;
import de.htwberlin.webtech.clubwm.web.api.Match;
import de.htwberlin.webtech.clubwm.web.api.Stadium;
import de.htwberlin.webtech.clubwm.web.api.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final GroupRepository groupRepository;
    private final TeamRepository teamRepository;
    private final StadiumRepository stadiumRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, GroupRepository groupRepository,
                        TeamRepository teamRepository, StadiumRepository stadiumRepository) {
        this.matchRepository = matchRepository;
        this.groupRepository = groupRepository;
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

        // Aktualisieren der Gruppenstatistiken nach Speichern des Matches
        updateGroupStandings();

        return convertEntityToApi(savedMatchEntity);
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

    public void updateGroupStandings() {
        List<MatchEntity> allMatches = matchRepository.findAll();
        Map<GroupEntity, List<GroupTeam>> groupStats = calculateStatsForAllGroups(allMatches);

        for (Map.Entry<GroupEntity, List<GroupTeam>> entry : groupStats.entrySet()) {
            GroupEntity group = entry.getKey();
            List<GroupTeam> teamsStats = entry.getValue();

            group.getTeams().forEach(team -> {
                GroupTeam updatedTeamStats = teamsStats.stream()
                        .filter(t -> Objects.equals(t.getTeam().getId(), team.getTeam().getId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Team not found in updated stats"));

                team.setMatches(updatedTeamStats.getMatches());
                team.setWins(updatedTeamStats.getWins());
                team.setDraws(updatedTeamStats.getDraws());
                team.setLosses(updatedTeamStats.getLosses());
                team.setGoalDifference(updatedTeamStats.getGoalDifference());
                team.setPoints(updatedTeamStats.getPoints());
            });

            groupRepository.save(group);
        }
    }

    private Map<GroupEntity, List<GroupTeam>> calculateStatsForAllGroups(List<MatchEntity> matches) {
        Map<GroupEntity, List<GroupTeam>> groupStats = new HashMap<>();

        for (MatchEntity match : matches) {
            GroupEntity homeGroup = findGroupByTeam(match.getHomeTeam());
            GroupEntity visitorGroup = findGroupByTeam(match.getVisitorTeam());

            // Assuming each team belongs to exactly one group
            if (!homeGroup.equals(visitorGroup)) {
                throw new IllegalStateException("Both teams must belong to the same group.");
            }

            // Calculate match results
            int homePoints = match.getHomeScore() > match.getVisitorScore() ? 3 :
                    match.getHomeScore() == match.getVisitorScore() ? 1 : 0;
            int visitorPoints = match.getVisitorScore() > match.getHomeScore() ? 3 :
                    match.getVisitorScore() == match.getHomeScore() ? 1 : 0;

            updateTeamStats(groupStats, homeGroup, match.getHomeTeam(), match.getHomeScore(),
                    match.getVisitorScore(), homePoints);
            updateTeamStats(groupStats, visitorGroup, match.getVisitorTeam(), match.getVisitorScore(),
                    match.getHomeScore(), visitorPoints);
        }

        // Sort each group's teams based on the criteria
        for (Map.Entry<GroupEntity, List<GroupTeam>> entry : groupStats.entrySet()) {
            List<GroupTeam> sortedTeams = entry.getValue().stream()
                    .sorted(Comparator.comparingInt(GroupTeam::getPoints).reversed()
                            .thenComparingInt(GroupTeam::getGoalDifference).reversed()
                            .thenComparingInt(GroupTeam::getGoalScored).reversed()
                            .thenComparingInt(t -> new Random().nextInt())) // Use a random tiebreaker
                    .collect(Collectors.toList());

            entry.setValue(sortedTeams);
        }

        return groupStats;
    }

    private GroupEntity findGroupByTeam(TeamEntity team) {
        return groupRepository.findAll().stream()
                .filter(group -> group.getTeams().stream()
                        .anyMatch(groupTeam -> groupTeam.getTeam().equals(team)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No group found for team " + team.getName()));
    }

    private void updateTeamStats(Map<GroupEntity, List<GroupTeam>> groupStats, GroupEntity group,
                                 TeamEntity team, int goalsScored, int goalsConceded, int points) {
        GroupTeam groupTeam = groupStats
                .computeIfAbsent(group, k -> new ArrayList<>()).stream()
                .filter(gt -> gt.getTeam().equals(team))
                .findFirst()
                .orElse(new GroupTeam(team));

        groupTeam.setMatches(groupTeam.getMatches() + 1);
        groupTeam.setGoalScored(groupTeam.getGoalScored() + goalsScored);
        groupTeam.setGoalDifference(groupTeam.getGoalDifference() + (goalsScored - goalsConceded));
        groupTeam.setPoints(groupTeam.getPoints() + points);

        if (goalsScored > goalsConceded) {
            groupTeam.setWins(groupTeam.getWins() + 1);
        } else if (goalsScored == goalsConceded) {
            groupTeam.setDraws(groupTeam.getDraws() + 1);
        } else {
            groupTeam.setLosses(groupTeam.getLosses() + 1);
        }

        groupStats.get(group).add(groupTeam);
    }
}