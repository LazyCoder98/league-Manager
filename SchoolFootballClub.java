package com.PremierLeague;

import java.util.Objects;

public class SchoolFootballClub extends FootballClub {
    private String schoolName;

    public SchoolFootballClub(String clubName, String location, int wins, int draws, int losses, int seasonsPlayed, int goalsReceived, int goalsScored, int numberOfPoints, int matchesPlayed, String schoolName) {
        super(clubName, location, wins, draws, losses, seasonsPlayed, goalsReceived, goalsScored, numberOfPoints, matchesPlayed);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "SchoolFootballClub{" + super.toString()+
                "schoolName='" + schoolName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return Objects.equals(schoolName, that.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolName);
    }


}
