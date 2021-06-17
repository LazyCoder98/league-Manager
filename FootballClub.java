package com.PremierLeague;

import java.util.Objects;

public class FootballClub extends SportsClub implements Comparable<SportsClub> {
//    creating variables
    private int wins;
    private int draws;
    private int losses;
    private int seasonsPlayed;
    private int goalsReceived;
    private int goalsScored;
    private int numberOfPoints;
    private int matchesPlayed;
    //modified constructor
    public FootballClub(String clubName, String location, int wins, int draws, int losses, int seasonsPlayed, int goalsReceived, int goalsScored, int numberOfPoints, int matchesPlayed) {
        super(clubName, location);
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.seasonsPlayed = seasonsPlayed;
        this.goalsReceived = goalsReceived;
        this.goalsScored = goalsScored;
        this.numberOfPoints = numberOfPoints;
        this.matchesPlayed = matchesPlayed;
    }
    //creating setters and getters
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getSeasonsPlayed() {
        return seasonsPlayed;
    }

    public void setSeasonsPlayed(int seasonsPlayed) {
        this.seasonsPlayed = seasonsPlayed;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
//overriding to string method
    @Override
    public String toString() {
        return "FootballClub{" + super.toString()+
                "wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", seasonsPlayed=" + seasonsPlayed +
                ", goalsReceived=" + goalsReceived +
                ", goalsScored=" + goalsScored +
                ", numberOfPoints=" + numberOfPoints +
                ", matchesPlayed=" + matchesPlayed +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub that = (FootballClub) o;
        return wins == that.wins &&
                draws == that.draws &&
                losses == that.losses &&
                seasonsPlayed == that.seasonsPlayed &&
                goalsReceived == that.goalsReceived &&
                goalsScored == that.goalsScored &&
                numberOfPoints == that.numberOfPoints &&
                matchesPlayed == that.matchesPlayed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, draws, losses, seasonsPlayed, goalsReceived, goalsScored, numberOfPoints, matchesPlayed);
    }
// overriding compareTo method for sorting


    @Override
    public int compareTo(SportsClub c1) {
        if(this.getNumberOfPoints()==((FootballClub)c1).getNumberOfPoints())
            return ((this.getGoalsScored()-this.getGoalsReceived())-(((FootballClub)c1).getGoalsScored()-((FootballClub)c1).getGoalsReceived()));
        else
            return (this.getNumberOfPoints()-((FootballClub)c1).getNumberOfPoints());
    }
}

