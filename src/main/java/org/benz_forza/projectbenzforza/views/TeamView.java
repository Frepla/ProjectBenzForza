package org.benz_forza.projectbenzforza.views;

import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.DAO.GameDAO;
import org.benz_forza.projectbenzforza.DAO.PlayerDAO;
import org.benz_forza.projectbenzforza.DAO.TeamDAO;
import org.benz_forza.projectbenzforza.entities.Game;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;
import java.util.List;

//JESPER
public class TeamView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox(30);
        root.setBackground(new Background(new BackgroundFill(Color.DARKRED,null,null)));
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Select an option for Team");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial Black",28));


        Button displayButton = new Button("Display All Teams");
        displayButton.setPrefWidth(150);
        displayButton.setPrefHeight(50);
        displayButton.setOnAction(e -> displayTeam());

        Button addButton = new Button("Add a new team");
        addButton.setPrefWidth(150);
        addButton.setPrefHeight(50);
        addButton.setOnAction(e -> addTeam());

        Button updateButton = new Button("Update Teams");
        updateButton.setPrefWidth(150);
        updateButton.setPrefHeight(50);
        updateButton.setOnAction(e -> updateTeam());

        Button deleteButton = new Button("Delete a Team");
        deleteButton.setPrefWidth(150);
        deleteButton.setPrefHeight(50);
        deleteButton.setOnAction(e -> deleteTeam());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefWidth(150);
        mainMenuButton.setPrefHeight(50);
        mainMenuButton.setOnAction(e->{
            MenuView menuView = new MenuView();
            try {
                menuView.start(stage);
            } catch (Exception ex) {
                System.out.println("Error loading MenuView: " + ex.getMessage());
            }
        });

        root.getChildren().addAll(title, displayButton, addButton, updateButton, deleteButton,mainMenuButton);

        Scene scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();

    }
    public void displayTeam() {
        Stage displayStage = new Stage();
        displayStage.setTitle("Display all Teams");

        ListView<String> listView = new ListView<>();

        List<Team> allTeams = TeamDAO.getAllTeams();

        ObservableList<String> teamsDetailsList = FXCollections.observableArrayList();

        for (Team team : allTeams) {
            String teamDetails = team.toString();


            teamsDetailsList.add(teamDetails);
        }

        listView.setItems(teamsDetailsList);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> displayStage.close());


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(listView, goBack);

        Scene scene = new Scene(layout, 800, 600);
        displayStage.setScene(scene);
        displayStage.show();

    }

    public void addTeam() {
        Stage addStage = new Stage();
        addStage.setTitle("Add a new Team");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(Double.MAX_VALUE);

        Label teamNameLabel = new Label("Enter new Team Name");
        TextField nameField = new TextField();
        ComboBox<Game> gamesComboBox = new ComboBox<>();

        Label selectGame = new Label("Select Game");

        List<Game> gameList = GameDAO.getAllGames();
        gamesComboBox.getItems().addAll(gameList);

        Button addButton = new Button("Add Team");

        addButton.setOnAction(e -> {
            String teamName = nameField.getText();
            Game selectedGame = gamesComboBox.getSelectionModel().getSelectedItem();

            if (teamName == null || teamName.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Team name is required!");
                return;
            }

            if (selectedGame == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a game!");
                return;
            }

            boolean saved = TeamDAO.saveTeam(teamName, selectedGame);

            if (saved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Team added successfully!");
                addStage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Team name already exists or saving failed.");
            }
        });



        grid.add(teamNameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(selectGame, 0, 1);
        grid.add(gamesComboBox, 1, 1);
        grid.add(addButton, 1, 2);

        Button goBack = new Button("Return");
        goBack.setOnAction(e->addStage.close());
        grid.add(goBack, 1, 8);


        Scene scene = new Scene(grid, 400, 250);
        addStage.setScene(scene);
        addStage.show();

    }private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void updateTeam() {
        Stage updateStage = new Stage();
        updateStage.setTitle("Select an option for Team");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(Double.MAX_VALUE);


        Label addLabel = new Label("Select to add a player");
        Label removeLabel = new Label("Select to remove a player");

        Button addPlayerButton = new Button("Add Player");
        addPlayerButton.setOnAction(e -> addPlayer());
        Button removePlayerButton = new Button("Remove Player");
        removePlayerButton.setOnAction(e -> removePlayer());

        grid.add(addLabel, 0, 0);
        grid.add(removeLabel, 0, 1);
        grid.add(addPlayerButton, 1, 0);
        grid.add(removePlayerButton, 1, 1);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> updateStage.close());
        grid.add(goBack, 1, 8);

        Scene scene = new Scene(grid, 800, 600);
        updateStage.setScene(scene);
        updateStage.show();
    }
    private void removePlayer() {
        Stage removePlayerStage = new Stage();
        removePlayerStage.setTitle("Remove Player from Team");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(Double.MAX_VALUE);

        Label playerLabel = new Label("Select Player:");
        Label currentTeamLabel = new Label("Current Team:");

        ComboBox<Player> playerComboBox = new ComboBox<>();
        playerComboBox.setPromptText("Select Player");

        TextField currentTeamField = new TextField();
        currentTeamField.setEditable(false);

        List<Player> players = PlayerDAO.getAllPlayers();
        playerComboBox.getItems().addAll(players);

        playerComboBox.setOnAction(event -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                if (selectedPlayer.getTeamId() != null && selectedPlayer.getTeamId().getId() != -1) {
                    String currentTeamName = PlayerDAO.getTeamNameById(selectedPlayer.getTeamId().getId());
                    currentTeamField.setText(currentTeamName);
                } else {
                    currentTeamField.setText("No Team");
                }
            }
        });

        Button removeButton = new Button("Remove from Team");
        removeButton.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null && selectedPlayer.getTeamId() != null) {
                boolean successful = PlayerDAO.updatePlayerTeam(selectedPlayer.getId(), -1); // Remove the player from team
                if (successful) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Player removed from team successfully!");
                    removePlayerStage.close();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Removing player from team failed!");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Player does not belong to any team.");
            }
        });

        grid.add(playerLabel, 0, 0);
        grid.add(playerComboBox, 1, 0);
        grid.add(currentTeamLabel, 0, 1);
        grid.add(currentTeamField, 1, 1);
        grid.add(removeButton, 1, 2);


        Button goBack = new Button("Return");
        goBack.setOnAction(e -> removePlayerStage.close());
        grid.add(goBack, 0, 3);

        Scene scene = new Scene(grid, 800, 600);
        removePlayerStage.setScene(scene);
        removePlayerStage.show();
    }
    private void addPlayer() {
        Stage addPlayerStage = new Stage();
        addPlayerStage.setTitle("Add Player to Team");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(Double.MAX_VALUE);


        Label playerLabel = new Label("Select Player:");
        Label currentTeamLabel = new Label("Current Team:");
        Label newTeamLabel = new Label("New Team:");

        ComboBox<Player> playerComboBox = new ComboBox<>();
        playerComboBox.setPromptText("Select Player");

        TextField currentTeamField = new TextField();
        currentTeamField.setEditable(false);

        ComboBox<Team> newTeamComboBox = new ComboBox<>();
        newTeamComboBox.setPromptText("Select New Team");

        Team noTeam = new Team();
        noTeam.setTeamName("No Team");
        noTeam.setId(-1);

        List<Player> players = PlayerDAO.getAllPlayers();
        playerComboBox.getItems().addAll(players);

        List<Team> teams = PlayerDAO.getAllTeams();
        newTeamComboBox.getItems().add(noTeam);
        newTeamComboBox.getItems().addAll(teams);


        playerComboBox.setOnAction(event -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                if (selectedPlayer.getTeamId() != null && selectedPlayer.getTeamId().getId() != -1) {
                    String currentTeamName = PlayerDAO.getTeamNameById(selectedPlayer.getTeamId().getId());
                    currentTeamField.setText(currentTeamName);
                } else {
                    currentTeamField.setText("No Team");
                }
            }
        });

        Button addButton = new Button("Add to Team");
        addButton.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            Team newTeam = newTeamComboBox.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null) {
                if (selectedPlayer.getTeamId() == null) {
                    boolean successful;

                    if (newTeam.getId() == -1) {

                        successful = true;
                    } else {
                        successful = PlayerDAO.updatePlayerTeam(selectedPlayer.getId(), newTeam.getId());
                    }

                    if (successful) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Player added to team successfully!");
                        addPlayerStage.close();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Error", "Adding player to team failed!");
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Player already belongs to a team.");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a player and a new team.");
            }
        });


        grid.add(playerLabel, 0, 0);
        grid.add(playerComboBox, 1, 0);
        grid.add(currentTeamLabel, 0, 1);
        grid.add(currentTeamField, 1, 1);
        grid.add(newTeamLabel, 0, 2);
        grid.add(newTeamComboBox, 1, 2);
        grid.add(addButton, 1, 3);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> addPlayerStage.close());
        grid.add(goBack, 0, 4);

        Scene scene = new Scene(grid, 800, 600);
        addPlayerStage.setScene(scene);
        addPlayerStage.show();
    }

    public void deleteTeam() {
        Stage deleteStage = new Stage();
        deleteStage.setTitle("Delete Team");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(Double.MAX_VALUE);

        Label title = new Label("Select Team to Delete");

        ComboBox<Team> teamComboBox = new ComboBox<>();
        List<Team> teams = TeamDAO.getAllTeams();
        teamComboBox.getItems().addAll(teams);

        Button deleteButton = new Button("Delete Team");
        deleteButton.setOnAction(e -> {
            Team selectedTeam = teamComboBox.getSelectionModel().getSelectedItem();

            if (selectedTeam != null) {
                boolean deleted = TeamDAO.deleteTeam(selectedTeam);

                if (deleted) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Team deleted successfully!");
                    teamComboBox.getSelectionModel().clearSelection();
                    teamComboBox.getItems().setAll(TeamDAO.getAllTeams());
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Team could not be deleted.");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a team to delete.");
            }
        });

        grid.add(title, 0, 0);
        grid.add(teamComboBox, 1, 0);
        grid.add(deleteButton, 1, 3);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> deleteStage.close());
        grid.add(goBack, 1, 8);

        Scene scene = new Scene(grid, 800, 600);
        deleteStage.setScene(scene);
        deleteStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
