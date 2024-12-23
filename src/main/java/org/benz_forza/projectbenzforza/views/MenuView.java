package org.benz_forza.projectbenzforza.views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
// JESPER
public class MenuView {
    public void start(Stage primaryStage) throws Exception {

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));

        Label titleLabel = new Label("PIPER GAMES");
        titleLabel.setFont(Font.font("Impact", FontWeight.BOLD, 32));
        titleLabel.setTextFill(Color.BLACK);

        Button playerButton = new Button("Player");
        playerButton.setPrefSize(150, 50);
        playerButton.setOnAction(e -> {
            Stage playerStage = new Stage();
            PlayerView playerView = new PlayerView();
            try {
                playerView.start(playerStage);
            } catch (Exception ex) {
                System.out.println("Error while trying to load page: " + ex.getMessage());
            }
        });

        Button teamButton = new Button("Team");
        teamButton.setPrefSize(150, 50);
        teamButton.setOnAction(e -> {
            Stage teamStage = new Stage();
            TeamView teamView = new TeamView();
            try {
               teamView.start(teamStage);
            } catch (Exception ex) {
                System.out.println("Error while trying to load page: " + ex.getMessage());
            }
        });


        Button gameButton = new Button("Game");
        gameButton.setPrefSize(150, 50);
        gameButton.setOnAction(e -> {
            Stage gameStage = new Stage();
            GameView gameView = new GameView();
            try {
              gameView.start(gameStage);
            } catch (Exception ex) {
                System.out.println("Error while trying to load page: " + ex.getMessage());
            }
        });

        Button matchButton = new Button("Match");
        matchButton.setPrefSize(150, 50);
        matchButton.setOnAction(e -> {
            Stage matchStage = new Stage();
            MatchView matchView = new MatchView();
            try {
               matchView.start(matchStage);
            } catch (Exception ex) {
                System.out.println("Error while trying to load page: " + ex.getMessage());
            }
        });

        Button staffButton = new Button("Staff");
        staffButton.setPrefSize(150, 50);
        staffButton.setOnAction(e -> {
            Stage staffStage = new Stage();
            StaffView staffView = new StaffView();
            try {
                staffView.start(staffStage);
            } catch (Exception ex) {
                System.out.println("Error while trying to load page: " + ex.getMessage());
            }
        });
        Button signOutButton = new Button("Sign Out");
        signOutButton.setPrefSize(150, 50);
        signOutButton.setOnAction(e -> {
            Stage signOutStage = new Stage();
            StartView signOutView = new StartView();
            try {
               signOutView.start(signOutStage);
               primaryStage.close();
            } catch (Exception ex) {
                System.out.println("Error while trying to load page: " + ex.getMessage());
            }
        });

        root.getChildren().addAll(titleLabel, playerButton, teamButton, gameButton, matchButton, staffButton,signOutButton);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Start View");
        primaryStage.show();
    }


    }

