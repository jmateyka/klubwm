package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.GroupEntity;
import de.htwberlin.webtech.clubwm.persistence.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }

    public GroupEntity getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));
    }

    public GroupEntity saveGroup(GroupEntity groupEntity) {
        return groupRepository.save(groupEntity);
    }

    public void calculateStandings(Long groupId) {
        // Business-Logik zur Tabellenberechnung basierend auf Matches
    }
}