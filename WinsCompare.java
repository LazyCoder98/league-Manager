package com.PremierLeague;

import java.util.Comparator;

//custom comparator to sort sportsClubS by wins
public class WinsCompare implements Comparator<SportsClub> {
    @Override
    public int compare(SportsClub s1, SportsClub s2) {
        if(((FootballClub)s1).getWins()>((FootballClub)s2).getWins())
            return 1;
        else
            return -1;
    }
}
