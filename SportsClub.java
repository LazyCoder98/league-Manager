package com.PremierLeague;
import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable{
    //    creating variables
    private String clubName;
    private String location;

    //modified constructor
    public SportsClub(String clubName, String location) {
        this.clubName = clubName;
        this.location = location;
    }

    //creating setters and getters
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Overriding  equals method
    @Override
    public boolean equals(Object t) {
        if (this == t) return true;
        if (t == null || getClass() != t.getClass()) return false;
        SportsClub that = (SportsClub) t;
        return Objects.equals(clubName, that.clubName) &&
                Objects.equals(location, that.location);
    }

    // Overriding hashcode method
    @Override
    public int hashCode() {
        return Objects.hash(clubName, location);
    }

    // Overriding tostring method
    @Override
    public String toString() {
        return "SportsClub{" +
                "clubName='" + clubName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}