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

    private double average_age;

    @Column(nullable = false)
    private String market_value;

    public TeamEntity() {}

    public TeamEntity(String name, String country, String location, String league, double average_age, String market_value) {
        this.name = name;
        this.country = country;
        this.location = location;
        this.league = league;
        this.average_age = average_age;
        this.market_value = market_value;
    }


    public long getId() {
        return id;
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
        return average_age;
    }

    public void setAverageAge(double average_age) {
        this.average_age = average_age;
    }

    public String getMarketValue() {
        return market_value;
    }

    public void setMarketValue(String market_value) {
        this.market_value = market_value;
    }
}

