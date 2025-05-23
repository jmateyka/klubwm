package de.htwberlin.webtech.clubwm.web;

import de.htwberlin.webtech.clubwm.service.GroupService;
import de.htwberlin.webtech.clubwm.persistence.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupRestController {

    private final GroupService groupService;

    @Autowired
    public GroupRestController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupEntity> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public GroupEntity getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("/{id}/calculate")
    public void calculateGroupStandings(@PathVariable Long id) {
        groupService.calculateStandings(id);
    }
}