package de.htwberlin.webtech.clubwm.persistence;

import jakarta.persistence.*;


@Entity(name = "Matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hometeamid", nullable = false)
    private TeamEntity homeTeam;

    @ManyToOne
    @JoinColumn(name = "visitorteamid", nullable = false)
    private TeamEntity visitorTeam;

    @Column(name = "homescore")
    private int homeScore;

    @Column(name = "visitorscore")
    private int visitorScore;

    @ManyToOne
    @JoinColumn(name = "stadiumid", nullable = false)
    private StadiumEntity stadium;

    protected MatchEntity() {}

    public MatchEntity(TeamEntity homeTeam, TeamEntity visitorTeam, int homeScore, int visitorScore, StadiumEntity stadium) {
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeScore = homeScore;
        this.visitorScore = visitorScore;
        this.stadium = stadium;
    }

    public Long getId() {
        return id;
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