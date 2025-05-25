package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.GroupEntity;
import de.htwberlin.webtech.clubwm.persistence.GroupRepository;
import de.htwberlin.webtech.clubwm.persistence.GroupTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
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
}