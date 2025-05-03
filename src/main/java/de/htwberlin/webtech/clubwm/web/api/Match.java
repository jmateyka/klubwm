package de.htwberlin.webtech.clubwm.web.api;

public class Match {
    private long id;
    private String homeTeam;
    private String visitorTeam;
    private int homeScore;
    private int visitorScore;


    public Match(long id, String homeTeam, String visitorTeam, int homeScore, int visitorScore) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeScore = homeScore;
        this.visitorScore = visitorScore;
    }

    // Getter und Setter
    public long getId() { return id; }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
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
}