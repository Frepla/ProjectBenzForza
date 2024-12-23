package org.benz_forza.projectbenzforza.views;

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
import org.benz_forza.projectbenzforza.DAO.GameDAO;
import org.benz_forza.projectbenzforza.entities.Game;

import java.util.List;
//Denise
public class GameView {
    public void start(Stage stage) {
        VBox root = new VBox(30);
        root.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Select an option for Game");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial Black", 28));

        Button displayButton = new Button("Display All Games");
        displayButton.setPrefWidth(150);
        displayButton.setPrefHeight(50);
        displayButton.setOnAction(e -> displayGames());

        Button addButton = new Button("Add a New Game");
        addButton.setPrefWidth(150);
        addButton.setPrefHeight(50);
        addButton.setOnAction(e -> addGame());

        Button updateButton = new Button("Update Game");
        updateButton.setPrefWidth(150);
        updateButton.setPrefHeight(50);
        updateButton.setOnAction(e -> updateGame());

        Button deleteButton = new Button("Delete a Game");
        deleteButton.setPrefWidth(150);
        deleteButton.setPrefHeight(50);
        deleteButton.setOnAction(e -> deleteGame());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefWidth(150);
        mainMenuButton.setPrefHeight(50);
        mainMenuButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(title, displayButton, addButton, updateButton, deleteButton, mainMenuButton);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void displayGames() {
        Stage displayStage = new Stage();
        displayStage.setTitle("Display All Games");

        ListView<String> listView = new ListView<>();
        List<Game> allGames = GameDAO.getAllGames();

        for (Game game : allGames) {
            listView.getItems().add(game.toString());
        }

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> displayStage.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(listView, goBack);

        Scene scene = new Scene(layout, 800, 600);
        displayStage.setScene(scene);
        displayStage.show();
    }

    public void addGame() {
        Stage addStage = new Stage();
        addStage.setTitle("Add a New Game");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Enter Game Name:");
        TextField nameField = new TextField();

        Button addButton = new Button("Add Game");
        addButton.setOnAction(e -> {
            String gameName = nameField.getText();

            if (gameName == null || gameName.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Game name is required!");
                return;
            }

            boolean saved = GameDAO.saveGame(gameName);

            if (saved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Game added successfully!");
                addStage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Game name already exists or saving failed.");
            }
        });

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> addStage.close());

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(addButton, 1, 1);
        grid.add(goBack, 1, 2);

        Scene scene = new Scene(grid, 400, 250);
        addStage.setScene(scene);
        addStage.show();
    }

    public void updateGame() {
        Stage updateStage = new Stage();
        updateStage.setTitle("Update Game");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label selectGameLabel = new Label("Select Game to Update:");
        ComboBox<Game> gameComboBox = new ComboBox<>();
        List<Game> games = GameDAO.getAllGames();
        gameComboBox.getItems().addAll(games);

        Label nameLabel = new Label("Enter New Game Name:");
        TextField nameField = new TextField();

        Button updateButton = new Button("Update Game");
        updateButton.setOnAction(e -> {
            Game selectedGame = gameComboBox.getSelectionModel().getSelectedItem();
            String newName = nameField.getText();

            if (selectedGame == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a game to update!");
                return;
            }

            if (newName == null || newName.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Game name is required!");
                return;
            }

            selectedGame.setGameName(newName);
            boolean updated = GameDAO.updateGame(selectedGame);

            if (updated) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Game updated successfully!");
                updateStage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Updating game failed.");
            }
        });

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> updateStage.close());

        grid.add(selectGameLabel, 0, 0);
        grid.add(gameComboBox, 1, 0);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(updateButton, 1, 2);
        grid.add(goBack, 1, 3);

        Scene scene = new Scene(grid, 400, 250);
        updateStage.setScene(scene);
        updateStage.show();
    }

    public void deleteGame() {
        Stage deleteStage = new Stage();
        deleteStage.setTitle("Delete Game");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label selectGameLabel = new Label("Select Game to Delete:");
        ComboBox<Game> gameComboBox = new ComboBox<>();
        List<Game> games = GameDAO.getAllGames();
        gameComboBox.getItems().addAll(games);

        Button deleteButton = new Button("Delete Game");
        deleteButton.setOnAction(e -> {
            Game selectedGame = gameComboBox.getSelectionModel().getSelectedItem();

            if (selectedGame != null) {
                boolean deleted = GameDAO.deleteGameById(selectedGame.getId());

                if (deleted) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Game deleted successfully!");
                    gameComboBox.getItems().setAll(GameDAO.getAllGames());
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Game could not be deleted.");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a game to delete.");
            }
        });

        Button goBack = new Button("Return");
        goBack.setOnAction(e -> deleteStage.close());

        grid.add(selectGameLabel, 0, 0);
        grid.add(gameComboBox, 1, 0);
        grid.add(deleteButton, 1, 1);
        grid.add(goBack, 1, 2);

        Scene scene = new Scene(grid, 400, 250);
        deleteStage.setScene(scene);
        deleteStage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
