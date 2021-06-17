package com.PremierLeague;

import java.util.Comparator;

//custom comparator to sort sportsClub by goals scored
public class GoalsCompare implements Comparator<SportsClub> {
    @Override
    public int compare(SportsClub s1, SportsClub s2) {
        if(((FootballClub)s1).getGoalsScored()>((FootballClub)s2).getGoalsScored())
            return 1;
        else
            return -1;
    }
}
