package de.htwberlin.webtech.clubwm.web.api;

import java.util.List;

public class Group {
    private Long id;
    private String name;
    private List<GroupTeamDTO> teams;

    public Group(Long id, String name, List<GroupTeamDTO> teams) {
        this.id = id;
        this.name = name;
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupTeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<GroupTeamDTO> teams) {
        this.teams = teams;
    }
}