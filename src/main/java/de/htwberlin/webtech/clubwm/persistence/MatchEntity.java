package de.htwberlin.webtech.clubwm.persistence;

import jakarta.persistence.*;

@Entity(name = "Matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private TeamEntity homeTeam;

    @ManyToOne
    @JoinColumn(name = "visitor_team_id", nullable = false)
    private TeamEntity visitorTeam;

    private int homeScore;
    private int visitorScore;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private StadiumEntity stadium;

    public MatchEntity() {}

    public MatchEntity(long id, TeamEntity homeTeam, TeamEntity visitorTeam, int homeScore, int visitorScore, StadiumEntity stadium) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeScore = homeScore;
        this.visitorScore = visitorScore;
        this.stadium = stadium;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TeamEntity getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamEntity homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamEntity getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(TeamEntity visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getVisitorScore() {
        return visitorScore;
    }

    public void setVisitorScore(int visitorScore) {
        this.visitorScore = visitorScore;
    }

    public StadiumEntity getStadium() {
        return stadium;
    }

    public void setStadium(StadiumEntity stadium) {
        this.stadium = stadium;
    }
}