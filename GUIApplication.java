package com.PremierLeague;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GUIApplication extends Application{
//    declaration  of variables and creating objects
    static PremierLeagueManager manager = new PremierLeagueManager();
    static CLIApplication cliManager = new CLIApplication();
    static Stage window;
    Scene leagueView , matchView;
    TableView<SportsClub> leagueTable;
    TableView<Match> matchesTable;
    Boolean searchBool = false;
    private List<Match> tempMatchList = new ArrayList<>();
    private static List<Match> randomMatchList = new ArrayList<>();

//main method
    public static void main(String[] args) throws IOException {
        try{
        manager.loadData("League.txt", "Matches.txt");
        }catch(FileNotFoundException e){
            new File("League.txt");
            new File("Matches.txt");
        }
        launch(args);

    }
//method to start gui
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Premiere League Table ");
        displayTable();
        cliManager.mainMenuLoop();

    }

//    method creating Premiere league table gui
    public void displayTable() {

        Label mainTitle = new Label("Premiere League Table");
        mainTitle.setStyle("-fx-text-fill: #ffffff");
        mainTitle.setFont(Font.font("Agency FB",30));
        mainTitle.setPadding(new Insets(0,0,10,0));

//        creating the columns for the table
        TableColumn<SportsClub, String> clubName = new TableColumn<>("Club Name ");
        clubName.setMaxWidth(250);
        clubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<SportsClub, Integer> wins = new TableColumn<>("Wins");
        wins.setMaxWidth(150);
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<SportsClub, Integer> draws = new TableColumn<>("Draws");
        draws.setMaxWidth(150);
        draws.setCellValueFactory(new PropertyValueFactory<>("draws"));

        TableColumn<SportsClub, Integer> losses = new TableColumn<>("Losses");
        losses.setMaxWidth(150);
        losses.setCellValueFactory(new PropertyValueFactory<>("losses"));

        TableColumn<SportsClub, Integer> goalsReceived = new TableColumn<>("Goals Received");
        goalsReceived.setMaxWidth(900);
        goalsReceived.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));

        TableColumn<SportsClub, Integer> goalsScored = new TableColumn<>("Goals Scored");
        goalsScored.setMaxWidth(600);
        goalsScored.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));

        TableColumn<SportsClub, Integer> points = new TableColumn<>("Points");
        points.setMaxWidth(150);
        points.setCellValueFactory(new PropertyValueFactory<>("numberOfPoints"));

        TableColumn<SportsClub, Integer> matches = new TableColumn<>("Matches Played");
        matches.setMaxWidth(900);
        matches.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));
// setting up the table
        leagueTable = new TableView<>();
        leagueTable.setMaxWidth(600);
        leagueTable.setItems(getLeagueData());
        leagueTable.getColumns().addAll(clubName, wins, draws, goalsReceived, goalsScored, points, matches);

//        creating the button to generating random match
        Button addRandomMatch =new Button();
        addRandomMatch.setText("Create Random Match ");
        addRandomMatch.setOnAction(e -> {
            matchRandomizer();
            displayTable();


        });

        Button exit = new Button();
        exit.setText("Exit to main menu");
        exit.setOnAction(e ->{
            window.close();
            try {
                cliManager.mainMenuLoop();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

//        creating ComboBox with sorting options
        ComboBox sortChoice = new ComboBox<>();
        sortChoice.getItems().add("Points in descending order");
        sortChoice.getItems().add("Goals in descending order");
        sortChoice.getItems().add("number of wins in descending order");
        sortChoice.setPromptText("Sort table ");

//setting functionality to sort
        sortChoice.setOnAction( e ->sortAction(sortChoice));
        Button matchesTableBtn = new Button();
        matchesTableBtn.setText("Match Table");
        matchesTableBtn.setOnAction(e ->displayMatchTable());

        VBox rightBox = new VBox();
        rightBox.setMargin(sortChoice,new Insets(10));
        rightBox.setMargin(matchesTableBtn,new Insets(10));
        rightBox.getChildren().addAll(sortChoice,matchesTableBtn, exit);


//        creating table to display table containing random generated matches
        Label randomizerLabel = new Label("Generate Random Played Match");
        randomizerLabel.setFont(Font.font("Agency FB",15));
        randomizerLabel.setStyle("-fx-text-fill: #ffffff");


        TableColumn<Match, String> teamAName = new TableColumn<>("Team A");
        teamAName.setMaxWidth(250);
        teamAName.setCellValueFactory(new PropertyValueFactory<>("team1"));

        TableColumn<Match, Integer> teamAScore = new TableColumn<>("Team A Score");
        teamAScore.setMaxWidth(250);
        teamAScore.setCellValueFactory(new PropertyValueFactory<>("score1"));

        TableColumn<Match, String> teamBName = new TableColumn<>("Team B");
        teamBName.setMaxWidth(250);
        teamBName.setCellValueFactory(new PropertyValueFactory<>("team2"));

        TableColumn<Match, Integer> teamBScore = new TableColumn<>("Team B Score");
        teamBScore.setMaxWidth(250);
        teamBScore.setCellValueFactory(new PropertyValueFactory<>("score2"));

//setting up table
        matchesTable = new TableView<>();
        matchesTable.setItems(getRandomMatchData());
        matchesTable.getColumns().addAll(teamAName,teamAScore,teamBName,teamBScore);

        VBox bottomBox = new VBox();
        bottomBox.setMaxSize(350,150);
        bottomBox.setSpacing(10);
        bottomBox.getChildren().addAll(randomizerLabel,matchesTable,addRandomMatch);

//setting up layout of the league table view
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(30,30,30,30));
        border.setTop(mainTitle);
        border.setAlignment(mainTitle,Pos.CENTER);
        border.setCenter(leagueTable);
        border.setMargin(leagueTable,new Insets(10));
        border.setRight(rightBox);
        border.setBottom(bottomBox);
        border.setStyle("-fx-background-color:#2c0232; ");
//setting up final scene
        leagueView = new Scene(border, 860, 700);
        window.setScene(leagueView);





    }

//    method to adding functionality to sorting
    private void sortAction(ComboBox combo) {
        if( combo.getValue().equals("Points in descending order")){
            Collections.sort(manager.getPremierLeague(),new PointsCompare().reversed());
            }
        else if( combo.getValue().equals("Goals in descending order")){
            Collections.sort(manager.getPremierLeague(),new GoalsCompare().reversed());
        }
        else if( combo.getValue().equals("number of wins in descending order")){
            Collections.sort(manager.getPremierLeague(),new WinsCompare().reversed());
        }
        displayTable();
    }

    private void displayMatchTable (){

        //      displaying played matches table
        Label titleLabel = new Label("Played Matches");
        titleLabel.setPadding(new Insets(10));
        titleLabel.setFont(Font.font("Agency FB",30));
        titleLabel.setStyle("-fx-text-fill: #ffffff");

//        creating columns for played match
        TableColumn<Match, String> teamAName = new TableColumn<>("Team A");
        teamAName.setMaxWidth(250);
        teamAName.setCellValueFactory(new PropertyValueFactory<>("team1"));

        TableColumn<Match, Integer> teamAScore = new TableColumn<>("Team A Score");
        teamAScore.setMaxWidth(250);
        teamAScore.setCellValueFactory(new PropertyValueFactory<>("score1"));

        TableColumn<Match, String> teamBName = new TableColumn<>("Team B");
        teamBName.setMaxWidth(250);
        teamBName.setCellValueFactory(new PropertyValueFactory<>("team2"));

        TableColumn<Match, Integer> teamBScore = new TableColumn<>("Team B Score");
        teamBScore.setMaxWidth(250);
        teamBScore.setCellValueFactory(new PropertyValueFactory<>("score2"));

        TableColumn<Match, String> matchDay = new TableColumn<>("Day");
        matchDay.setMaxWidth(250);
        matchDay.setCellValueFactory(new PropertyValueFactory<>("day"));

        TableColumn<Match, String> matchMonth = new TableColumn<>("Month");
        matchMonth.setMaxWidth(250);
        matchMonth.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<Match, String> matchYear = new TableColumn<>("Year");
        matchYear.setMaxWidth(250);
        matchYear.setCellValueFactory(new PropertyValueFactory<>("year"));
//setting up table
        matchesTable = new TableView<>();
        matchesTable.setItems(getPlayedMatchesData());
        matchesTable.getColumns().addAll(teamAName,teamAScore,teamBName,teamBScore,matchDay,matchMonth,matchYear);

        Label searchLabel = new Label("Search Played matches (Enter date in mm-dd-yyyy format)");
        searchLabel.setFont(Font.font("Agency FB",18));
        searchLabel.setStyle("-fx-text-fill: #ffffff");

//        creating text field and button for searching matches
        TextField matchSearchField = new TextField();
        Button matchSearchButton = new Button();
        matchSearchButton.setText("Search match");
        matchSearchButton.setOnAction( e -> matchesSearch(matchSearchField));

//        creating buttons to go back to league table and sort matches
        Button goBack = new Button();
        goBack.setText("Go back to League Table ");
        goBack.setOnAction( e ->displayTable());
        goBack.setPadding(new Insets(5));

        Button sortDateBnt = new Button();
        sortDateBnt.setText("Sort Table");
        sortDateBnt.setOnAction( e ->sortMatchTable());

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(goBack,sortDateBnt);
        buttonBox.setMargin(sortDateBnt,new Insets(10));
        buttonBox.setMargin(goBack,new Insets(10));



        VBox containerBox = new VBox();
        containerBox.setPadding(new Insets(15));
        containerBox.getChildren().addAll(searchLabel,matchSearchField,matchSearchButton);

//        finalizing layout of the played matches
        BorderPane border2 = new BorderPane();
        border2.setPadding(new Insets(10));
        border2.setTop(titleLabel);
        border2.setAlignment(titleLabel, Pos.CENTER);
        border2.setCenter(matchesTable);
        border2.setRight(containerBox);
        border2.setBottom(buttonBox);
        border2.setStyle("-fx-background-color:#2c0232");

        matchView = new Scene(border2, 880, 500);
        window.setTitle("Played Matches Table ");
        window.setScene(matchView);
        window.show();


    }

//    method to pass observable list for premiere league table
    public ObservableList<SportsClub> getLeagueData() {
        ObservableList <SportsClub> itemList = FXCollections.observableArrayList(manager.getPremierLeague());
        return itemList;
    }
    //    method to pass observable list for random generated matches
    public ObservableList<Match> getRandomMatchData() {
        ObservableList <Match>updateMatch = FXCollections.observableArrayList(randomMatchList);
        return updateMatch;

    }
    //    method to pass observable list for played matches tables
    public ObservableList<Match> getPlayedMatchesData() {

        if(searchBool == false  ) {
            tempMatchList.clear();
            tempMatchList.addAll(manager.getMatches());
        }
        ObservableList <Match>playedList = FXCollections.observableArrayList(tempMatchList);
        return playedList;

    }

//    method to sort played matches by date
    public void sortMatchTable(){
        Collections.sort(manager.getMatches(), new DatePlayedSort().reversed());
        displayMatchTable();
    }


//method to generate random match
    public void matchRandomizer() {
        String teamA;
        String teamB;
        int randomTeam1 = 0;
        int randomTeam2 = 0;
        int  date;
        int month;
        int year;
        if (manager.getPremierLeague().size()>1) {

//            creating random numbers to select teams
            while (randomTeam1 == randomTeam2){
            randomTeam1 = ThreadLocalRandom.current().nextInt(0, (manager.getPremierLeague().size() - 1) + 1);

            randomTeam2 = ThreadLocalRandom.current().nextInt(0, (manager.getPremierLeague().size() - 1) + 1);

            }
            teamA = manager.getPremierLeague().get(randomTeam1).getClubName();
            teamB = manager.getPremierLeague().get(randomTeam2).getClubName();

            date = ThreadLocalRandom.current().nextInt(1, 32);
            month = ThreadLocalRandom.current().nextInt(1, 13);
            year = 2020;

//adding randomly created match objects to played matches array
            int teamA_Score = ThreadLocalRandom.current().nextInt(0, 11);
            int teamB_Score = ThreadLocalRandom.current().nextInt(0, 11);
            randomMatchList.add(new Match(teamA, teamB, teamA_Score, teamB_Score, date, month, year));
            manager.addPlayedMatch(new Match(teamA, teamB, teamA_Score, teamB_Score, date, month, year));
        }else{
            System.out.println("Not enough teams in the League to create random pair ");
            displayTable();
        }

    }

//    method to search matches by played date
    public void matchesSearch(TextField input){
        Boolean emptyCheck = true  ;
        String dateInput;
            dateInput =input.getText();
//            validating input
            if(dateInput.matches("\\d{2}-\\d{2}-\\d{4}")){

                String [] dateArray = dateInput.split("-",3);
                int day = Integer.parseInt(dateArray[0]);
                int month = Integer.parseInt(dateArray[1]);
                int year = Integer.parseInt(dateArray[2]);
                tempMatchList.clear();
                System.out.println(tempMatchList);
                for (Match match : manager.getMatches()){
                    if (match.getDay()==day && match.getMonth() ==month && match.getYear() == year) {
                        emptyCheck = false ;
                        System.out.println(match);
                        searchBool = true;
                        tempMatchList.add(match);
                    }
                }

                if (emptyCheck == true ) {
                    System.out.println("No match records found ");
                }
                        displayMatchTable();
            }else {
                System.out.println("please enter date in dd-mm-yyyy format");

            }

    }
}