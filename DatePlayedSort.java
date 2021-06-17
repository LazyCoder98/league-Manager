package com.PremierLeague;

import java.util.Comparator;

//custom comparator to sort matches by date
public class DatePlayedSort implements Comparator <Match> {
    @Override
    public int compare(Match m1, Match m2) {
        return m1.compareTo(m2);


    }
}
