package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.GroupEntity;
import de.htwberlin.webtech.clubwm.persistence.GroupRepository;
import de.htwberlin.webtech.clubwm.persistence.GroupTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupEntity> getAllGroups() {
        // Verwende die Methode, um Gruppen mit Teams abzurufen
        return groupRepository.findAllGroupsWithTeams();
    }

    public GroupEntity getGroupById(Long id) {
        return groupRepository.findGroupWithTeamsById(id);
    }

    public GroupEntity saveGroup(GroupEntity groupEntity) {
        return groupRepository.save(groupEntity);
    }

    public void calculateStandings(Long groupId) {
        GroupEntity group = getGroupById(groupId); // Verwendet jetzt die neue Methode
        List<GroupTeam> teams = group.getTeams();

        // Sortiere die Teams nach Punkten, Tordifferenz und erzielten Toren, bei Gleichstand Zufall
        teams.sort(Comparator.comparingInt(GroupTeam::getPoints).reversed()
                .thenComparingInt(GroupTeam::getGoalDifference).reversed()
                .thenComparingInt(GroupTeam::getGoalScored).reversed()
                .thenComparing(t -> new Random().nextInt()));  // Zufällige Entscheidung bei weiterem Gleichstand

        group.setTeams(teams);
        groupRepository.save(group);
    }
}