package org.benz_forza.projectbenzforza.views;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
//JESPER
public class PlayerView {
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox(30);
        root.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,null,null)));
        root.setPadding(new Insets(20));
        root.setSpacing(10);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);


        Label titleLabel = new Label("PLAYER MENU");
        titleLabel.setFont(Font.font("Impact", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setAlignment(Pos.CENTER);

        Label addLabel = new Label("Add a new Player");
        addLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        addLabel.setTextFill(Color.BLACK);
        Button addButton = new Button("Add");
        addButton.setPrefWidth(60);
        addButton.setPrefHeight(20);
        addButton.setOnAction(e -> addWindow());

        Label displayLabel = new Label("Display Player Info");
        displayLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        displayLabel.setTextFill(Color.BLACK);
        Button displayButton = new Button("Display");
        displayButton.setPrefWidth(60);
        displayButton.setPrefHeight(20);
        displayButton.setOnAction(e -> displaysWindow());

        Label deleteLabel = new Label("Delete a Player");
        deleteLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        deleteLabel.setTextFill(Color.BLACK);
        Button deleteButton = new Button("Delete");
        deleteButton.setPrefWidth(60);
        deleteButton.setPrefHeight(20);
        deleteButton.setOnAction(e -> deleteWindow());

        Label updateLabel = new Label("Update Player Info");
        updateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        updateLabel.setTextFill(Color.BLACK);
        Button updateButton = new Button("Update");
        updateButton.setPrefWidth(60);
        updateButton.setPrefHeight(20);
        updateButton.setOnAction(e -> selectWindow());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefWidth(120);
        mainMenuButton.setPrefHeight(20);
        mainMenuButton.setOnAction(e->primaryStage.close());



        root.getChildren().addAll(titleLabel,grid,addLabel,addButton,displayLabel,displayButton,
                deleteLabel,deleteButton,updateLabel,updateButton,mainMenuButton);
        root.setAlignment(Pos.CENTER);

        grid.add(addLabel, 0, 0);
        grid.add(addButton, 1, 0);
        grid.add(displayLabel, 0, 1);
        grid.add(displayButton, 1, 1);
        grid.add(deleteLabel, 0, 2);
        grid.add(deleteButton, 1, 2);
        grid.add(updateLabel, 0, 3);
        grid.add(updateButton, 1, 3);

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
        grid.setMaxWidth(Double.MAX_VALUE);

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label nickNameLabel = new Label("Nickname:");
        TextField nickNameField = new TextField();

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        Label zipCodeLabel = new Label("Zip Code:");
        TextField zipCodeField = new TextField();

        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();

        Label countryLabel = new Label("Country:");
        TextField countryField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Button addButton = new Button("Add Player");
        addButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String nickName = nickNameField.getText();
            String address = addressField.getText();
            String zipCode = zipCodeField.getText();
            String city = cityField.getText();
            String country = countryField.getText();
            String email = emailField.getText();

            if (!firstName.isEmpty() && !lastName.isEmpty() && !nickName.isEmpty() &&
                    !address.isEmpty() && !zipCode.isEmpty() && !city.isEmpty() &&
                    !country.isEmpty() && !email.isEmpty()) {

                boolean successful = PlayerDAO.addPlayer(firstName, lastName, nickName, address, zipCode, city, country, email);

                if (successful) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Player added successfully!");
                    addStage.close();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Nickname or Email already exists!");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "All fields are required!");
            }
        });

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> addStage.close());

        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(nickNameLabel, 0, 2);
        grid.add(nickNameField, 1, 2);
        grid.add(addressLabel, 0, 3);
        grid.add(addressField, 1, 3);
        grid.add(zipCodeLabel, 0, 4);
        grid.add(zipCodeField, 1, 4);
        grid.add(cityLabel, 0, 5);
        grid.add(cityField, 1, 5);
        grid.add(countryLabel, 0, 6);
        grid.add(countryField, 1, 6);
        grid.add(emailLabel, 0, 7);
        grid.add(emailField, 1, 7);
        grid.add(addButton, 1, 8);
        grid.add(goBack, 1, 9);

        Scene scene = new Scene(grid, 800, 800);
        addStage.setScene(scene);
        addStage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void displaysWindow() {
        Stage displaysStage = new Stage();
        displaysStage.setTitle("Select Select");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(Double.MAX_VALUE);


        Label selectGameLabel = new Label("Select to view Game: ");
        Label selectDetailsLabel = new Label("Select to view details: ");

        Button playerInfoButton = new Button("Details info");
        playerInfoButton.setOnAction(e -> displayPlayerInfoWindow());
        Button playerGameButton = new Button("Game info");
        playerGameButton.setOnAction(e -> displayPlayerGameWindow());

        grid.add(selectGameLabel, 0, 0);
        grid.add(selectDetailsLabel, 0, 1);
        grid.add(playerGameButton, 1, 0);
        grid.add(playerInfoButton, 1, 1);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> displaysStage.close());
        grid.add(goBack, 1, 8);

        Scene scene = new Scene(grid, 800, 600);
        displaysStage.setScene(scene);
        displaysStage.show();
    }


    private void displayPlayerGameWindow() {
        Stage playerGameStage = new Stage();
        playerGameStage.setTitle("Display Players");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPrefWidth(500);

        Label label = new Label("Press CTRL or CMD to select many Players");

        ListView<Player> listView = new ListView<>();

        List<Player> allPlayers = PlayerDAO.getAllPlayers();
        listView.getItems().addAll(allPlayers);

        ListView<String> gameListView = new ListView<>();
        List<Game> allGames = PlayerDAO.getAllGames();

        List<String> gameNames = new ArrayList<>();
        gameNames.add("All Games");

        listView.setPrefWidth(500);
        gameListView.setPrefWidth(500);

        for (Game game : allGames) {
            gameNames.add(game.getGameName());
        }

        gameListView.getItems().addAll(gameNames);
        gameListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        gameListView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<String>) change -> {
            List<Player> filteredPlayers = new ArrayList<>();
            ObservableList<String> selectedGames = gameListView.getSelectionModel().getSelectedItems();

            for (Player player : allPlayers) {
                String teamName;
                if (player.getTeamId() != null) {
                    teamName = player.getTeamId().getTeamName();
                } else {
                    teamName = "No Team";
                }

                String gameName;
                if (player.getGameId() != null) {
                    gameName = player.getGameId().getGameName();
                } else {
                    gameName = "No Game";
                }

                if (selectedGames.contains("All Games") || selectedGames.contains(gameName)) {
                    filteredPlayers.add(player);
                }
            }

            listView.getItems().setAll(filteredPlayers);
        });

        grid.add(label, 0, 0);
        grid.add(gameListView, 0, 1);
        grid.add(listView, 0, 2);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> playerGameStage.close());
        grid.add(goBack, 0, 3);

        Scene scene = new Scene(grid, 800, 600);
        playerGameStage.setScene(scene);
        playerGameStage.show();
    }
    private void displayPlayerInfoWindow() {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("All Players");

        VBox detailsBox = new VBox(10);
        detailsBox.setPadding(new Insets(20));

        ListView<String> listView = new ListView<>();

        List<Player> allPlayers = PlayerDAO.getAllPlayers();

        ObservableList<String> playerDetailsList = FXCollections.observableArrayList();

        for (Player player : allPlayers) {

            String playerDetails = player.getFirstName() + " | " +
                    "\""+player.getNickName()+"\"" + " | " +
                    player.getLastName() + " | " +
                    player.getAddress() + " | " +
                    player.getZipCode() + " | " +
                    player.getCity() + " | " +
                    player.getCountry() + " | " +
                    player.getEmail() + " | " +
                    ((player.getTeamId() != null) ? player.getTeamId().getTeamName() : "No Team") + " | " +
                    ((player.getGameId() != null) ? player.getGameId().getGameName() : "No Game");


            playerDetailsList.add(playerDetails);
        }

        listView.setItems(playerDetailsList);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> detailsStage.close());


        detailsBox.getChildren().addAll(listView, goBack);

        Scene scene = new Scene(detailsBox, 800, 600);
        detailsStage.setScene(scene);
        detailsStage.show();

    }



    private void selectWindow() {
        Stage selectStage = new Stage();
        selectStage.setTitle("Select");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(Double.MAX_VALUE);


        Label selectLabel = new Label("Select to update:");

        Button teamButton = new Button("Update Team");
        teamButton.setOnAction(e -> teamWindow());
        Button gameButton = new Button("Update Game");
        gameButton.setOnAction(e -> gameWindow());

        grid.add(selectLabel, 1, 0);
        grid.add(teamButton, 1, 1);
        grid.add(gameButton, 1, 2);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> selectStage.close());
        grid.add(goBack, 1, 8);

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

        Button updateButton = new Button("Update Team");
        updateButton.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            Team newTeam = newTeamComboBox.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null) {
                boolean successful;

                if (newTeam != null && newTeam.getId() == -1) {

                    successful = PlayerDAO.updatePlayerTeam(selectedPlayer.getId(), -1);
                } else {
                    successful = PlayerDAO.updatePlayerTeam(selectedPlayer.getId(), newTeam.getId());
                }

                if (successful) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Player team updated successfully!");
                    teamStage.close();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Update Failed!");
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
        grid.add(updateButton, 1, 3);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> teamStage.close());
        grid.add(goBack, 1, 8);

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
        grid.setMaxWidth(Double.MAX_VALUE);


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
                currentGameField.setText("Player has no game");
            }
        });

        Button updateButton = new Button("Update Game");
        updateButton.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            Game newGame = newGameComboBox.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null && newGame != null) {
                boolean successful = PlayerDAO.updatePlayerGame(selectedPlayer.getId(), newGame.getId());

                if (successful) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Player's game updated successfully!");
                    gameStage.close();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Update failed!");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a player and a new game.");
            }
        });


        grid.add(playerLabel, 0, 0);
        grid.add(playerComboBox, 1, 0);
        grid.add(currentGameLabel, 0, 1);
        grid.add(currentGameField, 1, 1);
        grid.add(newGameLabel, 0, 2);
        grid.add(newGameComboBox, 1, 2);
        grid.add(updateButton, 1, 3);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> gameStage.close());
        grid.add(goBack, 1, 8);

        Scene scene = new Scene(grid, 800, 600);
        gameStage.setScene(scene);
        gameStage.show();
    }

    private void deleteWindow() {
        Stage deleteStage = new Stage();
        deleteStage.setTitle("Delete Player");

        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(Double.MAX_VALUE);



        Label warningLabel = new Label("Warning: Remove Player from Team before deletion.");
        warningLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        warningLabel.setTextFill(Color.RED);
        warningLabel.setAlignment(Pos.CENTER);
        warningLabel.setPadding(new Insets(20));
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
                boolean successful = PlayerDAO.deletePlayer(selectedPlayer);

                if (successful) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Player '" + selectedPlayer.getFirstName() + "' deleted successfully!");
                    playerComboBox.getItems().setAll(PlayerDAO.getAllPlayers());
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Player not found");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a player to delete.");
            }
        });


        grid.add(playerLabel, 0, 1);
        grid.add(playerComboBox, 1, 1);
        grid.add(deleteButton, 1, 2);

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> deleteStage.close());
        grid.add(goBack, 1, 8);

        vBox.getChildren().add(warningLabel);
        vBox.getChildren().add(grid);

        Scene scene = new Scene(vBox, 800, 600);
        deleteStage.setScene(scene);
        deleteStage.show();
    }

}