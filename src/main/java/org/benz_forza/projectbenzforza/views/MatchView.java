package org.benz_forza.projectbenzforza.views;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.DAO.GameDAO;
import org.benz_forza.projectbenzforza.DAO.MatchDAO;
import org.benz_forza.projectbenzforza.DAO.PlayerDAO;
import org.benz_forza.projectbenzforza.DAO.TeamDAO;
import org.benz_forza.projectbenzforza.entities.Game;
import org.benz_forza.projectbenzforza.entities.Match;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

// Fredrik
public class MatchView extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = createMainMenu();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Match View");
        primaryStage.show();
    }

    private VBox createMainMenu() {
        VBox root = new VBox(30);
        root.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, null, null)));
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Button listMatchesButton = createButton("List All Matches", () -> showMatchListWindow(false));
        Button listUpcomingButton = createButton("List Upcoming Matches", () -> showMatchListWindow(true));
        Button addButton = createButton("Add New Match", this::addWindow);

        root.getChildren().addAll(listMatchesButton, listUpcomingButton, addButton);
        return root;
    }

    private Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(e -> action.run());
        return button;
    }

    private void showMatchListWindow(boolean onlyUpcoming) {
        Stage listStage = new Stage();
        listStage.setTitle(onlyUpcoming ? "Upcoming Matches" : "All Matches");

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, null, null)));

        TableView<Match> matchTable = createMatchTable(fetchMatches(onlyUpcoming));

        Button backButton = createButton("Back", listStage::close);
        root.getChildren().addAll(matchTable, backButton);

        listStage.setScene(new Scene(root, 800, 600));
        listStage.show();
    }

    private TableView<Match> createMatchTable(List<Match> matches) {
        TableView<Match> matchTable = new TableView<>(FXCollections.observableArrayList(matches));

        TableColumn<Match, String> matchColumn = new TableColumn<>("Match Info");
        matchColumn.setCellValueFactory(data -> new SimpleStringProperty(getMatchInfo(data.getValue())));

        TableColumn<Match, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data -> new SimpleStringProperty(getMatchStatus(data.getValue())));

        matchTable.getColumns().addAll(matchColumn, statusColumn);
        return matchTable;
    }

    private String getMatchInfo(Match match) {
        if (match.getMatchType() == Match.MatchType.PLAYER_VS_PLAYER) {
            return match.getPlayer1().getFirstName() + " vs " + match.getPlayer2().getFirstName();
        }
        return match.getTeam1().getTeamName() + " vs " + match.getTeam2().getTeamName();
    }

    private String getMatchStatus(Match match) {
        return match.isFinished() ? "Finished - Winner: " + match.getWinner() : "Upcoming";
    }

    private List<Match> fetchMatches(boolean onlyUpcoming) {
        return onlyUpcoming ? MatchDAO.findUpcomingMatches() : MatchDAO.findAll();
    }

    private void addWindow() {
        Stage addStage = new Stage();
        addStage.setTitle("Add New Match");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        grid.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, null, null)));

        List<Game> games = GameDAO.getAllGames();
        List<Player> players = PlayerDAO.getAllPlayers();
        List<Team> teams = TeamDAO.getAllTeams();

        List<String> gameNames = games.stream()
                .map(Game::getGameName)
                .collect(Collectors.toList());

        ComboBox<Player> player1ComboBox = new ComboBox<>();
        ComboBox<Player> player2ComboBox = new ComboBox<>();
        ComboBox<Team> team1ComboBox = new ComboBox<>();
        ComboBox<Team> team2ComboBox = new ComboBox<>();
        ComboBox<String> gameComboBox = new ComboBox<>();

        player1ComboBox.setItems(FXCollections.observableArrayList(players));
        player2ComboBox.setItems(FXCollections.observableArrayList(players));
        team1ComboBox.setItems(FXCollections.observableArrayList(teams));
        team2ComboBox.setItems(FXCollections.observableArrayList(teams));
        gameComboBox.setItems(FXCollections.observableArrayList(gameNames));

        grid.addRow(0, new Label("Game:"), gameComboBox);

        ComboBox<String> matchTypeComboBox = createMatchTypeComboBox();
        grid.addRow(1, new Label("Match Type:"), matchTypeComboBox);

        grid.addRow(2, new Label("Player 1:"), player1ComboBox);
        grid.addRow(3, new Label("Player 2:"), player2ComboBox);

        grid.addRow(4, new Label("Team 1:"), team1ComboBox);
        grid.addRow(5, new Label("Team 2:"), team2ComboBox);

        TextField matchDateField = new TextField();
        TextField matchTimeField = new TextField();
        grid.addRow(6, new Label("Match Date (yyyy-MM-dd):"), matchDateField);
        grid.addRow(7, new Label("Match Time (HH:mm):"), matchTimeField);

        ComboBox<String> matchStatusComboBox = new ComboBox<>();
        matchStatusComboBox.setItems(FXCollections.observableArrayList("Upcoming", "Finished"));
        matchStatusComboBox.setValue("Upcoming");
        grid.addRow(8, new Label("Match Status:"), matchStatusComboBox);

        TextField resultField = new TextField();
        resultField.setPromptText("Enter Result");
        resultField.setDisable(true);
        grid.addRow(9, new Label("Result:"), resultField);

        matchStatusComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ("Finished".equals(newValue)) {
                resultField.setDisable(false);
            } else {
                resultField.setDisable(true);
            }
        });

        Button addButton = createButton("Add Match", () -> handleAddMatch(
                matchTypeComboBox,
                gameComboBox,
                player1ComboBox,
                player2ComboBox,
                team1ComboBox,
                team2ComboBox,
                matchDateField,
                matchTimeField,
                matchStatusComboBox,
                resultField,
                addStage
        ));
        Button backButton = createButton("Back", addStage::close);

        grid.addRow(10, addButton, backButton);

        matchTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            boolean isPlayerVsPlayer = "Player vs Player".equals(newValue);
            player1ComboBox.setDisable(!isPlayerVsPlayer);
            player2ComboBox.setDisable(!isPlayerVsPlayer);
            team1ComboBox.setDisable(isPlayerVsPlayer);
            team2ComboBox.setDisable(isPlayerVsPlayer);
        });

        team1ComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(team2ComboBox.getValue())) {
                showAlert(Alert.AlertType.WARNING, "Invalid Selection", "Both teams cannot be the same.");
                team2ComboBox.getSelectionModel().clearSelection();
            }
        });

        team2ComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(team1ComboBox.getValue())) {
                showAlert(Alert.AlertType.WARNING, "Invalid Selection", "Both teams cannot be the same.");
                team1ComboBox.getSelectionModel().clearSelection();
            }
        });

        matchTypeComboBox.setValue("Player vs Player");
        player1ComboBox.setDisable(false);
        player2ComboBox.setDisable(false);
        team1ComboBox.setDisable(true);
        team2ComboBox.setDisable(true);

        addStage.setScene(new Scene(grid, 800, 600));
        addStage.show();
    }

    private ComboBox<String> createMatchTypeComboBox() {
        ComboBox<String> matchTypeComboBox = new ComboBox<>();
        matchTypeComboBox.setItems(FXCollections.observableArrayList("Player vs Player", "Team vs Team"));
        matchTypeComboBox.setValue("Player vs Player");
        return matchTypeComboBox;
    }

    private void handleAddMatch(ComboBox<String> matchTypeSelection, ComboBox<String> gameComboBox, ComboBox<Player> player1ComboBox, ComboBox<Player> player2ComboBox,
                                ComboBox<Team> team1ComboBox, ComboBox<Team> team2ComboBox,
                                TextField matchDateField, TextField matchTimeField,
                                ComboBox<String> matchStatusComboBox, TextField resultField, Stage stage) {
        try {
            LocalDate date = LocalDate.parse(matchDateField.getText());
            LocalTime time = LocalTime.parse(matchTimeField.getText());

            String selectedGameName = gameComboBox.getValue();
            if (selectedGameName == null) {
                throw new IllegalArgumentException("Game must be selected.");
            }

            Game selectedGame = GameDAO.getAllGames().stream()
                    .filter(game -> game.getGameName().equals(selectedGameName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid game selection"));

            Match.MatchType matchType = matchTypeSelection.getValue().equals("Player vs Player") ? Match.MatchType.PLAYER_VS_PLAYER : Match.MatchType.TEAM_VS_TEAM;

            Match match;
            if (matchType == Match.MatchType.PLAYER_VS_PLAYER) {
                Player player1 = player1ComboBox.getValue();
                Player player2 = player2ComboBox.getValue();
                match = new Match(date.atTime(time), selectedGame, player1, player2, false, null); // matchen är inte klar och resultatet är null
            } else {
                Team team1 = team1ComboBox.getValue();
                Team team2 = team2ComboBox.getValue();
                match = new Match(date.atTime(time), selectedGame, team1, team2, false, null); // matchen är inte klar och resultatet är null
            }


            match.setFinished(matchStatusComboBox.getValue().equals("Finished"));
            if (match.isFinished()) {
                String result = resultField.getText();
                if (result == null || result.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Result must be entered for finished matches.");
                    return;
                }
                match.setResult(result);
            }

            MatchDAO.save(match);
            showAlert(Alert.AlertType.INFORMATION, "Match Added", "The match was successfully added.");
            stage.close();

        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date or time format.");
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
