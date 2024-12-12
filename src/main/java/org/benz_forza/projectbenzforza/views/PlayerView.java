package org.benz_forza.projectbenzforza.views;

import javafx.application.Application;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.DAO.PlayerDAO;
import org.benz_forza.projectbenzforza.entities.Game;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;

import java.util.ArrayList;
import java.util.List;

public class PlayerView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox(30);
        root.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        root.setPadding(new Insets(20));
        root.setSpacing(10);


        Label titleLabel = new Label("Player Menu");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setAlignment(Pos.CENTER);


        VBox addBox = new VBox();
        Button addButton = new Button("Add");
        addBox.getChildren().add(addButton);
        addButton.setOnAction(e -> addWindow());

        VBox displayBox = new VBox();
        Button showButton = new Button("Display");
        displayBox.getChildren().add(showButton);
        showButton.setOnAction(e -> displayWindow());

        VBox deleteBox = new VBox();
        Button deleteButton = new Button("Delete");
        deleteBox.getChildren().add(deleteButton);
        deleteButton.setOnAction(e -> deleteWindow());

        VBox updateBox = new VBox();
        Button updateButton = new Button("Update");
        updateBox.getChildren().add(updateButton);
        updateButton.setOnAction(e -> selectWindow());

        root.getChildren().addAll(titleLabel,displayBox, deleteBox, addBox, updateBox);

        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player View");
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> PlayerDAO.close());
    }

    private void addWindow() {
        Stage addStage = new Stage();
        addStage.setTitle("Add New Player");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label nickNameLabel = new Label("Nickname:");
        TextField nickNameField = new TextField();

        Button addButton = new Button("Add Player");
        addButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String nickName = nickNameField.getText();

            if (!firstName.isEmpty() && !lastName.isEmpty() && !nickName.isEmpty()) {
                boolean succesful= PlayerDAO.addPlayer(firstName, lastName, nickName);
                if (succesful) {
                    System.out.println("Player added successfully!");
                    addStage.close();
                }else{
                    System.out.println("Error. unsuccessful add player");
                }
            } else {
                System.out.println("All fields are required!");
            }
        });

        Button goBack = new Button("return");
        goBack.setOnAction(e -> addStage.close());

        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(nickNameLabel, 0, 2);
        grid.add(nickNameField, 1, 2);
        grid.add(addButton, 1, 3);
        grid.add(goBack, 1, 4);


        Scene scene = new Scene(grid, 800, 600);
        addStage.setScene(scene);
        addStage.show();
    }

    private void displayWindow() {
        Stage displayStage = new Stage();
        displayStage.setTitle("Display Players");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);


        Label label = new Label("All Players:");

        ListView<String> listView = new ListView<>();

        List<Player> allPlayers = PlayerDAO.getAllPlayers();

        List<String> playerDetails = new ArrayList<>();
        for (Player player : allPlayers) {
            String teamName = (player.getTeamId() != null) ? player.getTeamId().getTeamName() : "No Team";
            String gameName = (player.getGameId() != null) ? player.getGameId().getGameName() : "No Game";


            String playerInfo = player.getNickName() + " " + player.getLastName() + " | Team: " + teamName + " | Game: " + gameName;
            playerDetails.add(playerInfo);
        }

        listView.getItems().addAll(playerDetails);
        ListView<String> gameListView = new ListView<>();
        List<Game> allGames = PlayerDAO.getAllGames();

        List<String> gameNames = new ArrayList<>();
        gameNames.add("All Games");
        for (Game game : allGames) {
            gameNames.add(game.getGameName());
        }

        gameListView.getItems().addAll(gameNames);
        gameListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        gameListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<String>) change -> {
            List<String> filteredPlayerDetails = new ArrayList<>();

            ObservableList<String> selectedGames = gameListView.getSelectionModel().getSelectedItems();

            for (Player player : allPlayers) {
                String teamName = (player.getTeamId() != null) ? player.getTeamId().getTeamName() : "No Team";
                String gameName = (player.getGameId() != null) ? player.getGameId().getGameName() : "No Game";
                String playerInfo = player.getNickName() + " " + player.getLastName() + " | Team: " + teamName + " | Game: " + gameName;

                if (selectedGames.contains("All Games") || selectedGames.contains(gameName)) {
                    filteredPlayerDetails.add(playerInfo);
                }
            }

            listView.getItems().setAll(filteredPlayerDetails);
        });

        grid.add(label, 0, 0);
        grid.add(gameListView, 0, 1);
        grid.add(listView, 0, 2);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> displayStage.close());
        grid.add(goBack, 0, 3);

        Scene scene = new Scene(grid, 800, 800);
        displayStage.setScene(scene);
        displayStage.show();
    }

    private void selectWindow() {
        Stage selectStage = new Stage();
        selectStage.setTitle("Select Select");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label selectLabel = new Label("Select to update Team or Game");

        Button teamButton = new Button("Update Team");
        teamButton.setOnAction(e -> teamWindow());
        Button gameButton = new Button("Update Game");
        gameButton.setOnAction(e -> gameWindow());

        grid.add(selectLabel, 0, 0);
        grid.add(teamButton, 1, 0);
        grid.add(gameButton, 1, 1);


        Scene scene = new Scene(grid, 800, 600);
        selectStage.setScene(scene);
        selectStage.show();
    }

    private void teamWindow() {
        Stage teamStage = new Stage();
        teamStage.setTitle("Update Player");


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label playerLabel = new Label("Select Player:");
        Label currentTeamLabel = new Label("Current Team:");
        Label newTeamLabel = new Label("New Team:");

        ComboBox<Player> playerComboBox = new ComboBox<>();
        playerComboBox.setPromptText("Select Player");

        TextField currentTeamField = new TextField();
        currentTeamField.setEditable(false);

        ComboBox<Team> newTeamComboBox = new ComboBox<>();
        newTeamComboBox.setPromptText("Select New Team");

        List<Player> players = PlayerDAO.getAllPlayers();
        playerComboBox.getItems().addAll(players);
        System.out.println("Fetched Players: " + players);

        List<Team> teams = PlayerDAO.getAllTeams();
        newTeamComboBox.getItems().addAll(teams);
        System.out.println("Fetched Teams: " + teams);

        playerComboBox.setOnAction(event -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                String currentTeamName = PlayerDAO.getTeamNameById(selectedPlayer.getTeamId().getId());
                currentTeamField.setText(currentTeamName);
            }
        });

        Button updateButton = new Button("Update Team");
        updateButton.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            Team newTeam = newTeamComboBox.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null && newTeam != null) {
                boolean successful = PlayerDAO.updatePlayerTeam(selectedPlayer.getId(), newTeam.getId());

                if (successful) {
                    System.out.println("Player team updated successfully!");
                    teamStage.close();
                } else {
                    System.out.println("Error: Could not update player team.");
                }
            } else {
                System.out.println("Please select a player and a new team.");
            }
        });

        grid.add(playerLabel, 0, 0);
        grid.add(playerComboBox, 1, 0);
        grid.add(currentTeamLabel, 0, 1);
        grid.add(currentTeamField, 1, 1);
        grid.add(newTeamLabel, 0, 2);
        grid.add(newTeamComboBox, 1, 2);
        grid.add(updateButton, 1, 3);

        Scene scene = new Scene(grid, 800, 600);
        teamStage.setScene(scene);
        teamStage.show();
    }

    private void gameWindow() {
        Stage gameStage = new Stage();
        gameStage.setTitle("Update Player");


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label playerLabel = new Label("Select Player:");
        Label currentGameLabel = new Label("Current Game:");
        Label newGameLabel = new Label("New Game:");

        ComboBox<Player> playerComboBox = new ComboBox<>();
        playerComboBox.setPromptText("Select Player");

        TextField currentGameField = new TextField();
        currentGameField.setEditable(false);

        ComboBox<Game> newGameComboBox = new ComboBox<>();
        newGameComboBox.setPromptText("Select New Game");

        List<Player> players = PlayerDAO.getAllPlayers();
        playerComboBox.getItems().addAll(players);
        System.out.println("Fetched Players: " + players);

        List<Game> games = PlayerDAO.getAllGames();
        newGameComboBox.getItems().addAll(games);
        System.out.println("Fetched Teams: " + games);

        playerComboBox.setOnAction(event -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                String currentGameName = PlayerDAO.getGameNameById(selectedPlayer.getGameId().getId());
                currentGameField.setText(currentGameName);
            }else{
                currentGameField.setText("PLayer has no game");
            }
        });

        Button updateButton = new Button("Update Game");
        updateButton.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            Game newGame = newGameComboBox.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null && newGame != null) {
                boolean successful = PlayerDAO.updatePlayerGame(selectedPlayer.getId(), newGame.getId());

                if (successful) {
                    System.out.println("Player game updated successfully!");
                    gameStage.close();
                } else {
                    System.out.println("Error: Could not update player game.");
                }
            } else {
                System.out.println("Please select a player and a new game.");
            }
        });

        grid.add(playerLabel, 0, 0);
        grid.add(playerComboBox, 1, 0);
        grid.add(currentGameLabel, 0, 1);
        grid.add(currentGameField, 1, 1);
        grid.add(newGameLabel, 0, 2);
        grid.add(newGameComboBox, 1, 2);
        grid.add(updateButton, 1, 3);

        Scene scene = new Scene(grid, 800, 600);
        gameStage.setScene(scene);
        gameStage.show();
    }

    private void deleteWindow() {
        Stage deleteStage = new Stage();
        deleteStage.setTitle("Delete Player");


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);


        Label playerLabel = new Label("Select Player:");

        ComboBox<Player> playerComboBox = new ComboBox<>();
        playerComboBox.setPromptText("Select Player");

        List<Player> players = PlayerDAO.getAllPlayers();
        playerComboBox.getItems().addAll(players);
        System.out.println("Fetched Players: " + players);


        Button deleteButton = new Button("Delete Player");
        deleteButton.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null) {
                PlayerDAO.deletePlayer(selectedPlayer);
                System.out.println("Player "+selectedPlayer.getFirstName()+" deleted successfully!");

                playerComboBox.getSelectionModel().clearSelection();
                playerComboBox.getItems().addAll(PlayerDAO.getAllPlayers());
            }else {
                System.out.println("Player "+selectedPlayer.getFirstName()+" not found!");
            }
        });

        grid.add(playerLabel, 0, 0);
        grid.add(playerComboBox, 1, 0);
        grid.add(deleteButton, 1, 3);

        Scene scene = new Scene(grid, 800, 600);
        deleteStage.setScene(scene);
        deleteStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
