package de.htwberlin.webtech.clubwm.web.api;

import de.htwberlin.webtech.clubwm.web.api.Team;
import de.htwberlin.webtech.clubwm.web.api.Stadium;


public class Match {
    private long id;
    private Team homeTeam;
    private Team visitorTeam;
    private int homeScore;
    private int visitorScore;
    private Stadium stadium;

    public Match(long id, Team homeTeam, Team visitorTeam, int homeScore, int visitorScore, Stadium stadium) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeScore = homeScore;
        this.visitorScore = visitorScore;
        this.stadium = stadium;
    }

    // Getter und Setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }

    public Team getVisitorTeam() { return visitorTeam; }
    public void setVisitorTeam(Team visitorTeam) { this.visitorTeam = visitorTeam; }

    public int getHomeScore() { return homeScore; }
    public void setHomeScore(int homeScore) { this.homeScore = homeScore; }

    public int getVisitorScore() { return visitorScore; }
    public void setVisitorScore(int visitorScore) { this.visitorScore = visitorScore; }

    public Stadium getStadium() { return stadium; }
    public void setStadium(Stadium stadium) { this.stadium = stadium; }
}