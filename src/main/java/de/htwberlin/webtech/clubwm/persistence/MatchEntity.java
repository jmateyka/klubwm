package de.htwberlin.webtech.clubwm.persistence;

import jakarta.persistence.*;

@Entity(name = "Matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false)
    private String homeTeam;
    @Column(nullable = false)
    private String visitorTeam;
    private int homeScore;
    private int visitorScore;

    public MatchEntity(long id, String homeTeam, String visitorTeam, int homeScore, int visitorScore) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeScore = homeScore;
        this.visitorScore = visitorScore;
    }

    protected MatchEntity() {
    }

    public long getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getVisitorScore() {
        return visitorScore;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setVisitorScore(int visitorScore) {
        this.visitorScore = visitorScore;
    }
}
