package de.htwberlin.webtech.clubwm.persistence;

import jakarta.persistence.*;

@Entity
public class GroupTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TeamEntity team;

    private int matches = 0;
    private int wins = 0;
    private int draws = 0;
    private int losses = 0;
    private int goalDifference = 0;
    private int goalScored = 0;
    private int points = 0;

    protected GroupTeam() {}

    public GroupTeam(TeamEntity team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(int goalScored) {
        this.goalScored = goalScored;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}