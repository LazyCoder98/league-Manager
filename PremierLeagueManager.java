package com.PremierLeague;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PremierLeagueManager implements LeagueManager{
    public static final int MAX_CLUBS= 20;
    private List<SportsClub> premierLeague = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();


//    method to add club to premiere league
    @Override
    public void addSportsClub(SportsClub club) {
        if (premierLeague.contains(club)){
            System.out.println("The club is already in the premier league");
            return;
        }

        if(premierLeague.size()<MAX_CLUBS) {
            premierLeague.add(club);
            System.out.println("Club has been successfully added to League");
        }
        else {
            System.out.println("The premier League is Full");
        }

    }
    //    method to Delete a club from  premiere league
    @Override
    public void deleteClub(String clubName) {

        for(SportsClub club : premierLeague){
            if (club.getClubName().equals(clubName)){
                premierLeague.remove(club);
                System.out.println(club.getClubName()+"Club has been removed from the premier league");
                return;
            }
        }
        System.out.println("The club "+clubName+" is not in the premiere league!!, Please try again! ");

    }
    //    method to print statics of a specific club in the premier league
    @Override
    public void displayStatics(String clubName) {
        for (SportsClub club : premierLeague){
            if(club.getClubName().equals(clubName)){
                System.out.println("╔═══════════════════════════════════════════════╗");
                System.out.println("║              ══ Club Statistics ══            ║");
                System.out.println("╔═══════════════════════════════════════════════╗");
                System.out.println(" Club name :"+club.getClubName());
                System.out.println(" Wins: "+((FootballClub)club).getWins());
                System.out.println(" Draws: "+((FootballClub)club).getDraws());
                System.out.println(" Losses: "+((FootballClub)club).getLosses());
                System.out.println(" Goals Received: "+((FootballClub)club).getGoalsReceived());
                System.out.println(" Goals Scored: "+((FootballClub)club).getGoalsScored());
                System.out.println(" Points: "+((FootballClub)club).getNumberOfPoints());
                System.out.println(" Matches Played : "+((FootballClub)club).getMatchesPlayed());
                System.out.println("╚═══════════════════════════════════════════════╝");
                return;
            }

        }
        System.out.println("A club with the name "+clubName+" was not found");
    }
//method to display all the clubs in premiere league an their statistics
    @Override
    public void displayLeagueTable() {
       Collections.sort(premierLeague,Collections.reverseOrder());
        if (premierLeague.isEmpty()){
            System.out.println("Premier league is empty ");
        }
        else{
            System.out.println("==================================================================================================================");
            System.out.printf("%-20s%-10s%-10s%-10s%-20s%-20s%-10s%-10s\n", "Club", "Wins", "Draws", "Losses", "Goals Received", "Goals Scored", "Points", "Matches Played");
            System.out.println("==================================================================================================================");
            for (SportsClub club : premierLeague) {

                System.out.printf("%-21s%-10d%-12d%-12d%-20d%-16d%-15d%-10d\n", club.getClubName(), ((FootballClub)club).getWins(), ((FootballClub)club).getDraws(), ((FootballClub)club).getLosses(), ((FootballClub)club).getGoalsReceived(), ((FootballClub)club).getGoalsScored(), ((FootballClub)club).getNumberOfPoints(), ((FootballClub)club).getMatchesPlayed());
            }
            System.out.println("=================================================================================================================");
        }


    }

//    method to add matches that has be previously played
    @Override
    public void addPlayedMatch(Match match) {

        SportsClub teamA= null;
        SportsClub teamB=null;
        boolean foundA = false;
        boolean foundB = false;

//        validating if the clubs are in the league array
        for(SportsClub club : premierLeague){
            if (club.getClubName().equals(match.getTeam1())){
                foundA = true;
                teamA =club;
            }
        }



        for (SportsClub club : premierLeague){
            if(club.getClubName().equals(match.getTeam2())){
                foundB = true;
                teamB = club;
            }
        }

        if (!foundA){
            System.out.println("The club "+match.getTeam1()+" is not in the League" );
        }
        if (!foundB){
            System.out.println("The club  "+match.getTeam2()+" is not in the League" );
        }
//        adding points to the teams by checking if the match is a draw or adding points to winning team
        if (foundA && foundB){
            matches.add(match);
            ((FootballClub)teamA).setGoalsScored(((FootballClub) teamA).getGoalsScored()+match.getScore1());
            ((FootballClub)teamB).setGoalsScored(((FootballClub) teamB).getGoalsScored()+match.getScore2());
            ((FootballClub)teamA).setGoalsReceived(((FootballClub) teamA).getGoalsReceived()+match.getScore2());
            ((FootballClub)teamB).setGoalsReceived(((FootballClub) teamB).getGoalsReceived()+match.getScore1());
            ((FootballClub) teamA).setMatchesPlayed(((FootballClub) teamA).getMatchesPlayed() + 1);
            ((FootballClub) teamB).setMatchesPlayed(((FootballClub) teamB).getMatchesPlayed() + 1);

            if (match.getScore1()>match.getScore2()) {
                ((FootballClub) teamA).setNumberOfPoints(((FootballClub) teamA).getNumberOfPoints() + 3);
                ((FootballClub) teamA).setWins(((FootballClub) teamA).getWins() + 1);
                ((FootballClub) teamB).setLosses(((FootballClub) teamB).getLosses() + 1);
            }
            else  if ((match.getScore1()<match.getScore2())){
                ((FootballClub) teamB).setNumberOfPoints(((FootballClub) teamB).getNumberOfPoints() + 3);
                ((FootballClub) teamB).setWins(((FootballClub) teamB).getWins() + 1);
                ((FootballClub) teamA).setLosses(((FootballClub) teamA).getLosses() + 1);
            }
            else{
                ((FootballClub) teamA).setNumberOfPoints(((FootballClub) teamA).getNumberOfPoints() + 1);
                ((FootballClub) teamB).setNumberOfPoints(((FootballClub) teamB).getNumberOfPoints() + 1);
                ((FootballClub) teamA).setDraws(((FootballClub) teamA).getDraws() + 1);
                ((FootballClub) teamB).setDraws(((FootballClub) teamB).getDraws() + 1);}

        }
    }
//method to save data to file
    @Override
    public void saveData(String file, String file2) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        FileOutputStream fos2 = new FileOutputStream(file2);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
//writing objects in array to file
        for (SportsClub club:premierLeague){
            oos.writeObject(club);
        }
        System.out.println("Data successfully Saved ");
        fos.close();
        oos.close();
        oos=new ObjectOutputStream(fos2);
        for (Match match:matches){
            oos.writeObject(match);
        }

        fos2.close();
        oos.close();

    }
//method for loading data from file
    @Override
    public void loadData(String file,String file2) throws IOException{

        FileInputStream fis = new FileInputStream(file);
        FileInputStream fis2 = new FileInputStream(file2);
//reading objects in file  then typecasted and added into arrays
        ObjectInputStream ois = new ObjectInputStream(fis);
        for(;;){

            try {
               SportsClub club = (SportsClub) ois.readObject();
               premierLeague.add(club);

            } catch (EOFException|ClassNotFoundException e ) {
               break;
            }

        }

        fis.close();
        ois.close();
        ois = new ObjectInputStream(fis2);
        for(;;){

            try {
                Match match = (Match) ois.readObject();
                matches.add(match);

            } catch (EOFException|ClassNotFoundException e ) {

                break;
            }
        }
        fis2.close();
        ois.close();
        System.out.println("Data successfully loaded ");

    }
//getters for private arrays in the class
    public List<SportsClub> getPremierLeague() {
        return premierLeague;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
