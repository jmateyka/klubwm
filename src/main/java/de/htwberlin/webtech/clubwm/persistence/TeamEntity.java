package de.htwberlin.webtech.clubwm.persistence;

import jakarta.persistence.*;

@Entity(name = "Teams")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String league;

    private double averageAge;

    @Column(nullable = false)
    private String marketValue;

    public TeamEntity() {}

    public TeamEntity(String name, String country, String location, String league, double averageAge, String marketValue) {
        this.name = name;
        this.country = country;
        this.location = location;
        this.league = league;
        this.averageAge = averageAge;
        this.marketValue = marketValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public double getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(double averageAge) {
        this.averageAge = averageAge;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }
}