package com.PremierLeague;

import java.util.Comparator;

//custom comparator to sort sportsclubs by points
public class PointsCompare implements Comparator<SportsClub> {
    @Override
    public int compare(SportsClub c1, SportsClub c2) {
        if(((FootballClub)c1).getNumberOfPoints()>((FootballClub)c2).getNumberOfPoints())
            return 1;
        else
            return -1;
    }
}
