package com.PremierLeague;

import java.io.IOException;

import java.util.Scanner;

public class CLIApplication {
//    declaration of variables
    static PremierLeagueManager manager = new PremierLeagueManager();
    static GUIApplication guiManager = new GUIApplication();
    final static Scanner userInput= new Scanner(System.in);


//    method containing the loop for main menu
    public static void mainMenuLoop() throws IOException {

//        mainMenu:
//        while (true){
            displayMenu();
            int input = userInput.nextInt();

            switch (input){
                case 1:
                    addSportsClub();
                    break;
                case 2:
                    deleteClub();
                    break;
                case 3:
                    displayStats();
                    break;
                case 4:
                    manager.displayLeagueTable();
                    break;
                case 5:
                    addPlayedMatch();
                    break;

                case 6:
                    guiManager.window.show();
                    break;

                case 7:
                    manager.saveData("League.txt","Matches.txt");
                    System.out.println("Thank you for using the program ");
                    System.exit(0);
//                    break mainMenu;
                default:
                    System.out.println("You Selected an Invalid Option. Please Try Again!");
            }

//        }

    }
//    method to display the menu options
    public static void displayMenu(){
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║  ══ Welcome to the premiere league manager ══ ║");
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║ Press 1 to add new club                       ║");
        System.out.println("║ Press 2 to delete existing club               ║ ");
        System.out.println("║ Press 3 to get statistics of a club           ║ ");
        System.out.println("║ Press 4 to display premiere league table      ║ ");
        System.out.println("║ Press 5 to add a played match                 ║ ");
        System.out.println("║ Press 6 to start GUI                          ║ ");
        System.out.println("║ Press 7 to exit Program                       ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }


//method to invoke start display statistics method
    private static void displayStats() {
        System.out.println("Enter name of club to display statistics ");
        String clubName = userInput.next().toLowerCase();
        manager.displayStatics(clubName);

    }
//method to delete clubs in the premierLeague array
    private static void deleteClub() {
        System.out.println("Enter name of club to delete from premiere league ");
        String name = userInput.next().toLowerCase();
        manager.deleteClub(name);
    }

//    method to input data to add club
    private static void addSportsClub() {
        SportsClub sportsClub;

        System.out.println("Enter name of Club : ");
        String name = userInput.next().toLowerCase();

        System.out.println("Enter club Location : ");
        String location =userInput.next();

        System.out.println("Enter number of Wins : ");
        int wins = userInput.nextInt();

        System.out.println("Enter number of Draws : ");
        int draws = userInput.nextInt();

        System.out.println("Number of Losses : ");
        int losses = userInput.nextInt();

        System.out.println("Number of Seasons played : ");
        int seasonsPlayed = userInput.nextInt();

        System.out.println("Goals received by team : ");
        int goalsReceived = userInput.nextInt();

        System.out.println("Goals scored by team : ");
        int goalsScored = userInput.nextInt();

        System.out.println("Number of team Points : ");
        int points = userInput.nextInt();

        System.out.println("Number of matches Played : ");
        int noMatches = userInput.nextInt();

        sportsClub = new FootballClub(name,location,wins,draws,losses,seasonsPlayed,goalsReceived,goalsScored,points,noMatches);

        manager.addSportsClub(sportsClub);
    }

//    method to enter data and validate for adding played played matech
    private static void addPlayedMatch() {
        Match match;
        System.out.println("Enter name of team 1");
        String team1 = userInput.next().toLowerCase();


        System.out.println("Enter score of team 1");
        int score1 = userInput.nextInt();

        System.out.println("Enter name of Team 2");
        String team2 = userInput.next().toLowerCase();

        System.out.println("Enter score of team 2");
        int score2 = userInput.nextInt();

        System.out.println("Enter day of match");
        int day= 0;
        day = userInput.nextInt();
        while (day <0 || day >31 ) {
            System.out.println("invalid Day");
            day = userInput.nextInt();
        }

        System.out.println("Enter Month of match");
        int month = userInput.nextInt();
        while (day <0 || day >12 ) {
            System.out.println("invalid Month");
            month = userInput.nextInt();
        }

        System.out.println("Enter Year of match");
        int year = userInput.nextInt();

        match= new Match(team1,team2,score1,score2,day,month,year);
        manager.addPlayedMatch(match);

        System.out.println("Match details successfully updated ");
    }

}
