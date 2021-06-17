package com.PremierLeague;

import java.util.Objects;

public class UniversityFootballClub extends FootballClub {
    private String universityName;

    public UniversityFootballClub(String clubName, String location, int wins, int draws, int losses, int seasonsPlayed, int goalsReceived, int goalsScored, int numberOfPoints, int matchesPlayed, String universityName) {
        super(clubName, location, wins, draws, losses, seasonsPlayed, goalsReceived, goalsScored, numberOfPoints, matchesPlayed);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "UniversityFootballClub{" + super.toString()+
                "universityName='" + universityName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return Objects.equals(universityName, that.universityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), universityName);
    }
}
