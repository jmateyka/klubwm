package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.GroupEntity;
import de.htwberlin.webtech.clubwm.persistence.GroupRepository;
import de.htwberlin.webtech.clubwm.persistence.GroupTeam;
import de.htwberlin.webtech.clubwm.persistence.MatchEntity;
import de.htwberlin.webtech.clubwm.persistence.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Map<GroupEntity, List<GroupTeam>> groupStats = calculateStatsForAllGroups(matches);

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
            calculateStandings(group.getId());
        }
    }

    private Map<GroupEntity, List<GroupTeam>> calculateStatsForAllGroups(List<MatchEntity> matches) {
        Map<GroupEntity, List<GroupTeam>> groupStats = new HashMap<>();

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

            updateTeamStats(groupStats, homeGroup, match.getHomeTeam(), match.getHomeScore(),
                    match.getVisitorScore(), homePoints);
            updateTeamStats(groupStats, visitorGroup, match.getVisitorTeam(), match.getVisitorScore(),
                    match.getHomeScore(), visitorPoints);
        }

        groupStats.forEach((group, teams) -> teams.sort(
                Comparator.comparingInt(GroupTeam::getPoints).reversed()
                        .thenComparingInt(GroupTeam::getGoalDifference).reversed()
                        .thenComparingInt(GroupTeam::getGoalScored).reversed()
                        .thenComparingInt(t -> new Random().nextInt())
        ));

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
                .orElseGet(() -> {
                    GroupTeam newTeamStats = new GroupTeam(team);
                    groupStats.get(group).add(newTeamStats);
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