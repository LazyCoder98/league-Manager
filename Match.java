package com.PremierLeague;

import java.io.Serializable;

import java.util.Objects;

public class Match implements Serializable, Comparable<Match>{
//    declaring variables
    private int day ;
    private int month ;
    private int year;
    private String team1;
    private String team2;
    private int score1;
    private int score2;
//custom constructor
    public Match(String team1, String team2, int score1, int score2,int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
    }
//setters and getters for the variables
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

//method to convert the date into minutes
    private int convertToMinutes(){
        return this.day*24*60+this.month*30*24*60*+this.year*365*24*60;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return day == match.day &&
                month == match.month &&
                year == match.year &&
                score1 == match.score1 &&
                score2 == match.score2 &&
                Objects.equals(team1, match.team1) &&
                Objects.equals(team2, match.team2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year, team1, team2, score1, score2);
    }
//overriding to string method
    @Override
    public String toString() {
        return "Match{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }
//override compare to method
    @Override
    public int compareTo(Match o) {
        return this.convertToMinutes()-o.convertToMinutes();
    }
}


