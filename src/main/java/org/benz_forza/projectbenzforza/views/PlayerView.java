package org.benz_forza.projectbenzforza.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.TestPersistence;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;

import java.util.List;

public class PlayerView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox(30);


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


        VBox updateBox = new VBox();
        Button updateButton = new Button("Update");
        updateBox.getChildren().add(updateButton);
        updateButton.setOnAction(e -> updateWindow());

        root.getChildren().addAll(displayBox, deleteBox, addBox, updateBox);

        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player View");
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> TestPersistence.close());
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
                 boolean succesful= TestPersistence.addPlayer(firstName, lastName, nickName);
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


        Scene scene = new Scene(grid, 300, 200);
        addStage.setScene(scene);
        addStage.show();
    }

    private void displayWindow() {
        Stage didplayStage = new Stage();
        didplayStage.setTitle("Display Players");

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
                boolean succesful= TestPersistence.addPlayer(firstName, lastName, nickName);
                if (succesful) {
                    System.out.println("Player added successfully!");
                    didplayStage.close();
                }else{
                    System.out.println("Error. unsuccessful add player");
                }
            } else {
                System.out.println("All fields are required!");
            }
        });

        Button goBack = new Button("return");
        goBack.setOnAction(e -> didplayStage.close());

        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(nickNameLabel, 0, 2);
        grid.add(nickNameField, 1, 2);
        grid.add(addButton, 1, 3);
        grid.add(goBack, 1, 4);


        Scene scene = new Scene(grid, 300, 200);
        didplayStage.setScene(scene);
        didplayStage.show();
    }

    private void updateWindow() {
        Stage updateStage = new Stage();
        updateStage.setTitle("Update Player");


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

        List<Player> players = TestPersistence.getAllPlayers();
        playerComboBox.getItems().addAll(players);
        System.out.println("Fetched Players: " + players);

        List<Team> teams = TestPersistence.getAllTeams();
        newTeamComboBox.getItems().addAll(teams);
        System.out.println("Fetched Teams: " + teams);

        playerComboBox.setOnAction(event -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                String currentTeamName = TestPersistence.getTeamNameById(selectedPlayer.getTeamId().getId());
                currentTeamField.setText(currentTeamName);
            }
        });

        Button updateButton = new Button("Update Team");
        updateButton.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            Team newTeam = newTeamComboBox.getSelectionModel().getSelectedItem();

            if (selectedPlayer != null && newTeam != null) {
                boolean successful = TestPersistence.updatePlayer(selectedPlayer.getId(), newTeam.getId());

                if (successful) {
                    System.out.println("Player team updated successfully!");
                    updateStage.close();
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

        // Skapa scen och visa fönstret
        Scene scene = new Scene(grid, 400, 250);
        updateStage.setScene(scene);
        updateStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//        VBox root = new VBox(30);
//
//
//        VBox showBox = new VBox();
//        Button showButton = new Button("Show");
//        showBox.getChildren().add(showButton);
//        showButton.setOnAction(e -> showMeny());
//
//        VBox deleteBox = new VBox();
//        Button deleteButton = new Button("Delete");
//        deleteBox.getChildren().add(deleteButton);
//
//
//        VBox switchBox = new VBox();
//        Button switchButton = new Button("Switch");
//        deleteBox.getChildren().add(switchButton);
//
//        root.getChildren().addAll(showBox, deleteBox, addBox, switchBox);
//
//        Scene scene = new Scene(root, 400, 400);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void showMeny(){
//        Stage stage = new Stage();
//        VBox showMenyBox = new VBox();
//        Scene showMenyScene = new Scene(showMenyBox,500,500);
//        stage.setScene(showMenyScene);
//        stage.show();
//
//        Label label = new Label("Display the player");
//        showMenyBox.getChildren().add(label);
//        showMenyBox.setAlignment(Pos.CENTER);
//
//
//
//
//    }
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}
