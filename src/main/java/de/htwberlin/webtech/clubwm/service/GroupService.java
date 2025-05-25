package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.GroupEntity;
import de.htwberlin.webtech.clubwm.persistence.GroupRepository;
import de.htwberlin.webtech.clubwm.persistence.GroupTeam;
import de.htwberlin.webtech.clubwm.persistence.MatchEntity;
import de.htwberlin.webtech.clubwm.persistence.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupEntity getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with id: " + groupId));
    }

    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }

    public void calculateStandings(Long groupId) {
        GroupEntity group = getGroupById(groupId);
        List<GroupTeam> teams = group.getTeams();

        teams.sort(Comparator.comparingInt(GroupTeam::getPoints).reversed()
                .thenComparingInt(GroupTeam::getGoalDifference).reversed()
                .thenComparingInt(GroupTeam::getGoalScored).reversed()
                .thenComparingInt(t -> new Random().nextInt()));

        group.setTeams(teams);
        groupRepository.save(group);
    }

    public void updateGroupStandings(List<MatchEntity> matches) {
        // Holen aller Gruppen und Zurücksetzen der Statistiken
        for (GroupEntity group : groupRepository.findAll()) {
            for (GroupTeam team : group.getTeams()) {
                team.setMatches(0);
                team.setWins(0);
                team.setDraws(0);
                team.setLosses(0);
                team.setGoalDifference(0);
                team.setGoalScored(0);
                team.setPoints(0);
            }
        }

        // Berechnung der Statistiken basierend auf den Matches
        for (MatchEntity match : matches) {
            GroupEntity homeGroup = findGroupByTeam(match.getHomeTeam());
            GroupEntity visitorGroup = findGroupByTeam(match.getVisitorTeam());

            if (!homeGroup.equals(visitorGroup)) {
                throw new IllegalStateException("Both teams must belong to the same group.");
            }

            int homePoints = match.getHomeScore() > match.getVisitorScore() ? 3 :
                    match.getHomeScore() == match.getVisitorScore() ? 1 : 0;
            int visitorPoints = match.getVisitorScore() > match.getHomeScore() ? 3 :
                    match.getVisitorScore() == match.getHomeScore() ? 1 : 0;

            updateTeamStats(homeGroup, match.getHomeTeam(), match.getHomeScore(), match.getVisitorScore(), homePoints);
            updateTeamStats(visitorGroup, match.getVisitorTeam(), match.getVisitorScore(), match.getHomeScore(), visitorPoints);
        }

        // Speichern der gruppierten Teams
        for (GroupEntity group : groupRepository.findAll()) {
            calculateStandings(group.getId());
        }
    }

    private GroupEntity findGroupByTeam(TeamEntity team) {
        return groupRepository.findAll().stream()
                .filter(group -> group.getTeams().stream()
                        .anyMatch(groupTeam -> groupTeam.getTeam().equals(team)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No group found for team " + team.getName()));
    }

    private void updateTeamStats(GroupEntity group, TeamEntity team, int goalsScored, int goalsConceded, int points) {
        GroupTeam groupTeam = group.getTeams().stream()
                .filter(gt -> gt.getTeam().equals(team))
                .findFirst()
                .orElseGet(() -> {
                    GroupTeam newTeamStats = new GroupTeam(team);
                    group.getTeams().add(newTeamStats);
                    return newTeamStats;
                });

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
    }
}