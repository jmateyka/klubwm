package de.htwberlin.webtech.clubwm.persistence;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<GroupTeam> teams = new ArrayList<>();

    protected GroupEntity() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<GroupTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<GroupTeam> teams) {
        this.teams = teams;
    }
}