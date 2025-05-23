package de.htwberlin.webtech.clubwm.persistence;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupTeam> teams = new ArrayList<>();

    protected GroupEntity() {}

    public GroupEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<GroupTeam> getTeams() {
        return teams;
    }

    public void addTeam(GroupTeam groupTeam) {
        this.teams.add(groupTeam);
    }
}