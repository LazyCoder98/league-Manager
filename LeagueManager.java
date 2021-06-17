package com.PremierLeague;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface LeagueManager {

    void addSportsClub(SportsClub club);
    void deleteClub(String clubName);
    void displayStatics (String clubName);
    void displayLeagueTable();
    void addPlayedMatch(Match match);
    void saveData(String file ,String file2) throws IOException;
    void loadData(String file, String file2) throws IOException, ClassNotFoundException;

}
