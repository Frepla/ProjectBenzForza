package org.benz_forza.projectbenzforza.views;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.DAO.GameDAO;
import org.benz_forza.projectbenzforza.DAO.MatchDAO;
import org.benz_forza.projectbenzforza.DAO.PlayerDAO;
import org.benz_forza.projectbenzforza.DAO.TeamDAO;
import org.benz_forza.projectbenzforza.entities.Match;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;
import org.benz_forza.projectbenzforza.entities.Game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Fredrik
public class MatchView extends Application {

    private EntityManager entityManager;
    private final MatchDAO matchDAO = new MatchDAO();
    private final TableView<Match> matchTableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myconfig");
        entityManager = emf.createEntityManager();

        VBox root = createMatchView();

        Scene scene = new Scene(root, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Match View");
        primaryStage.show();
    }

    private VBox createMatchView() {
        VBox root = new VBox(10);

        matchTableView.setStyle(
                "-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-table-cell-border-color: black; " +
                "-fx-grid-lines-visible: true;"
        );


        matchTableView.setItems(getAllMatches());

        TableColumn<Match, String> matchTypeColumn = new TableColumn<>("Match Type");
        matchTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatchType()));
        matchTypeColumn.setMinWidth(100);
        matchTypeColumn.setPrefWidth(120);

        TableColumn<Match, String> gameColumn = new TableColumn<>("Game");
        gameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGame().getGameName()));
        gameColumn.setMinWidth(100);
        gameColumn.setPrefWidth(120);

        TableColumn<Match, String> player1Column = new TableColumn<>("Player 1");
        player1Column.setCellValueFactory(cellData -> {
            Player player1 = cellData.getValue().getPlayer1();
            String player1Name = player1 != null ? player1.getFirstName() + " " + player1.getLastName() : "N/A";
            return new SimpleStringProperty(player1Name);
        });
        player1Column.setMinWidth(100);
        player1Column.setPrefWidth(120);

        TableColumn<Match, String> player2Column = new TableColumn<>("Player 2");
        player2Column.setCellValueFactory(cellData -> {
            Player player2 = cellData.getValue().getPlayer2();
            String player2Name = player2 != null ? player2.getFirstName() + " " + player2.getLastName() : "N/A";
            return new SimpleStringProperty(player2Name);
        });
        player2Column.setMinWidth(100);
        player2Column.setPrefWidth(120);

        TableColumn<Match, String> team1Column = new TableColumn<>("Team 1");
        team1Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeam1() != null ? cellData.getValue().getTeam1().getTeamName() : "N/A"));
        team1Column.setMinWidth(100);
        team1Column.setPrefWidth(120);

        TableColumn<Match, String> team2Column = new TableColumn<>("Team 2");
        team2Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeam2() != null ? cellData.getValue().getTeam2().getTeamName() : "N/A"));
        team2Column.setMinWidth(100);
        team2Column.setPrefWidth(120);

        TableColumn<Match, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMatchDate()));
        dateColumn.setMinWidth(100);
        dateColumn.setPrefWidth(120);

        TableColumn<Match, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatchStatus()));
        statusColumn.setMinWidth(100);
        statusColumn.setPrefWidth(120);

        TableColumn<Match, String> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(cellData -> {
            String result = cellData.getValue().getMatchResult();
            return new SimpleStringProperty(result != null && !result.isEmpty() ? result : "TBD");
        });
        resultColumn.setMinWidth(100);
        resultColumn.setPrefWidth(120);


        TableColumn<Match, String> winnerColumn = new TableColumn<>("Winner");
        winnerColumn.setCellValueFactory(cellData -> {
            String winner = cellData.getValue().getMatchWinner();
            return new SimpleStringProperty(winner != null && !winner.isEmpty() ? winner : "TBD");
        });
        winnerColumn.setMinWidth(100);
        winnerColumn.setPrefWidth(120);


        List<TableColumn<Match, ?>> columns = new ArrayList<>();
        columns.add(matchTypeColumn);
        columns.add(gameColumn);
        columns.add(player1Column);
        columns.add(player2Column);
        columns.add(team1Column);
        columns.add(team2Column);
        columns.add(dateColumn);
        columns.add(statusColumn);
        columns.add(resultColumn);
        columns.add(winnerColumn);

        matchTableView.getColumns().addAll(columns);

        Button listAllMatchesButton = new Button("List All Matches");
        listAllMatchesButton.setOnAction(event -> matchTableView.setItems(getAllMatches()));

        Button listUpcomingMatchesButton = new Button("List Upcoming Matches");
        listUpcomingMatchesButton.setOnAction(event -> matchTableView.setItems(getUpcomingMatches()));

        Button addMatchButton = new Button("Add Match");
        addMatchButton.setOnAction(event -> showAddMatchDialog());

        Button updateMatchButton = new Button("Update Match");
        updateMatchButton.setOnAction(event -> showUpdateMatchDialog());

        Button deleteMatchButton = new Button("Delete Match");
        deleteMatchButton.setOnAction(event -> showDeleteMatchDialog());

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeWindow(closeButton));

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(
                listAllMatchesButton,
                listUpcomingMatchesButton,
                addMatchButton,
                updateMatchButton,
                deleteMatchButton,
                closeButton
        );

        root.getChildren().addAll(matchTableView, buttonBox);

        return root;
    }

    private ObservableList<Match> getAllMatches() {
        return FXCollections.observableArrayList(matchDAO.getAllMatches());
    }

    private ObservableList<Match> getUpcomingMatches() {
        return FXCollections.observableArrayList(matchDAO.getUpcomingMatches());
    }

    private void showAddMatchDialog() {
        VBox addMatchLayout = new VBox(10);
        addMatchLayout.setPadding(new Insets(20));

        ComboBox<String> matchTypeComboBox = new ComboBox<>();
        matchTypeComboBox.getItems().addAll("Player vs Player", "Team vs Team");

        ComboBox<Game> gameTypeComboBox = new ComboBox<>();
        ObservableList<Game> allGames = FXCollections.observableArrayList(getAllGames());
        gameTypeComboBox.setItems(allGames);

        gameTypeComboBox.setCellFactory(param -> new ListCell<Game>() {
            @Override
            protected void updateItem(Game item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getGameName());
                }
            }
        });

        gameTypeComboBox.setButtonCell(new ListCell<Game>() {
            @Override
            protected void updateItem(Game item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getGameName());
                }
            }
        });

        ComboBox<Player> player1ComboBox = new ComboBox<>();
        ComboBox<Player> player2ComboBox = new ComboBox<>();
        player1ComboBox.setItems(FXCollections.observableArrayList(getAllPlayers()));
        player2ComboBox.setItems(FXCollections.observableArrayList(getAllPlayers()));

        ComboBox<Team> team1ComboBox = new ComboBox<>();
        ComboBox<Team> team2ComboBox = new ComboBox<>();
        team1ComboBox.setItems(FXCollections.observableArrayList(getAllTeams()));
        team2ComboBox.setItems(FXCollections.observableArrayList(getAllTeams()));

        DatePicker matchDatePicker = new DatePicker();

        ComboBox<String> matchStatusComboBox = new ComboBox<>();
        matchStatusComboBox.getItems().addAll("Upcoming", "Finished");

        TextField matchResultTextField = new TextField();
        matchResultTextField.setPromptText("Match Result");

        ComboBox<String> matchWinnerComboBox = new ComboBox<>();

        matchStatusComboBox.setOnAction(event -> {
            String selectedStatus = matchStatusComboBox.getValue();
            boolean isFinished = "Finished".equals(selectedStatus);
            matchResultTextField.setVisible(isFinished);
            matchWinnerComboBox.setVisible(isFinished);
        });

        matchTypeComboBox.setOnAction(event -> {
            String selectedMatchType = matchTypeComboBox.getValue();
            if ("Player vs Player".equals(selectedMatchType)) {
                player1ComboBox.setDisable(false);
                player2ComboBox.setDisable(false);
                team1ComboBox.setDisable(true);
                team2ComboBox.setDisable(true);
            } else {
                player1ComboBox.setDisable(true);
                player2ComboBox.setDisable(true);
                team1ComboBox.setDisable(false);
                team2ComboBox.setDisable(false);
            }
        });

        gameTypeComboBox.setOnAction(event -> {
            Game selectedGame = gameTypeComboBox.getValue();
            if (selectedGame != null) {
                ObservableList<Player> playersForGame = getPlayersForGame(selectedGame);
                ObservableList<Team> teamsForGame = getTeamsForGame(selectedGame);

                player1ComboBox.setItems(playersForGame);
                player2ComboBox.setItems(playersForGame);
                team1ComboBox.setItems(teamsForGame);
                team2ComboBox.setItems(teamsForGame);

                if ("Player vs Player".equals(matchTypeComboBox.getValue())) {
                    player1ComboBox.setDisable(false);
                    player2ComboBox.setDisable(false);
                    team1ComboBox.setDisable(true);
                    team2ComboBox.setDisable(true);
                } else {
                    player1ComboBox.setDisable(true);
                    player2ComboBox.setDisable(true);
                    team1ComboBox.setDisable(false);
                    team2ComboBox.setDisable(false);
                }
            }
        });

        player1ComboBox.setOnAction(event -> updateMatchWinnerComboBox(matchTypeComboBox, player1ComboBox, player2ComboBox, team1ComboBox, team2ComboBox, matchWinnerComboBox));
        player2ComboBox.setOnAction(event -> updateMatchWinnerComboBox(matchTypeComboBox, player1ComboBox, player2ComboBox, team1ComboBox, team2ComboBox, matchWinnerComboBox));
        team1ComboBox.setOnAction(event -> updateMatchWinnerComboBox(matchTypeComboBox, player1ComboBox, player2ComboBox, team1ComboBox, team2ComboBox, matchWinnerComboBox));
        team2ComboBox.setOnAction(event -> updateMatchWinnerComboBox(matchTypeComboBox, player1ComboBox, player2ComboBox, team1ComboBox, team2ComboBox, matchWinnerComboBox));

        Button saveButton = new Button("Save Match");
        saveButton.setOnAction(event -> {
            String result = matchResultTextField.getText();
            if (matchStatusComboBox.getValue().equals("Finished") && (result == null || !result.matches("\\d+-\\d+"))) {
                showAlert(Alert.AlertType.ERROR, "Invalid Result", "Match result must be in the format 'x-x' (e.g., '2-1').");
                return;
            }

            try {
                Match newMatch = createNewMatch(
                        matchTypeComboBox.getValue(),
                        gameTypeComboBox.getValue(),
                        player1ComboBox.getValue(),
                        player2ComboBox.getValue(),
                        team1ComboBox.getValue(),
                        team2ComboBox.getValue(),
                        matchDatePicker.getValue(),
                        matchStatusComboBox.getValue(),
                        matchResultTextField.getText(),
                        matchWinnerComboBox.getValue()
                );

                matchDAO.addMatch(newMatch);
                matchTableView.setItems(FXCollections.observableArrayList(matchDAO.getAllMatches()));
                matchTypeComboBox.setValue(null);
                gameTypeComboBox.setValue(null);
                player1ComboBox.setValue(null);
                player2ComboBox.setValue(null);
                team1ComboBox.setValue(null);
                team2ComboBox.setValue(null);
                matchDatePicker.setValue(null);
                matchStatusComboBox.setValue(null);
                matchResultTextField.clear();
                matchWinnerComboBox.setValue(null);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Match added successfully!");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Something went wrong while adding the match.");
            }
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeWindow(closeButton));

        HBox buttonLayout = new HBox(10);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(saveButton, closeButton);

        addMatchLayout.getChildren().addAll(
                new Label("Match Type:"), matchTypeComboBox,
                new Label("Game:"), gameTypeComboBox,
                new Label("Player 1:"), player1ComboBox,
                new Label("Player 2:"), player2ComboBox,
                new Label("Team 1:"), team1ComboBox,
                new Label("Team 2:"), team2ComboBox,
                new Label("Match Date:"), matchDatePicker,
                new Label("Match Status:"), matchStatusComboBox,
                matchResultTextField, matchWinnerComboBox, buttonLayout
        );

        Stage addMatchStage = new Stage();
        addMatchStage.setScene(new Scene(addMatchLayout, 400, 600));
        addMatchStage.setTitle("Add New Match");
        addMatchStage.show();
    }


    private void updateMatchWinnerComboBox(ComboBox<String> matchTypeComboBox, ComboBox<Player> player1ComboBox, ComboBox<Player> player2ComboBox, ComboBox<Team> team1ComboBox, ComboBox<Team> team2ComboBox, ComboBox<String> matchWinnerComboBox) {
        matchWinnerComboBox.getItems().clear();
        if ("Player vs Player".equals(matchTypeComboBox.getValue())) {
            if (player1ComboBox.getValue() != null && player2ComboBox.getValue() != null) {
                matchWinnerComboBox.getItems().addAll(
                        player1ComboBox.getValue().getFirstName() + " " + player1ComboBox.getValue().getLastName(),
                        player2ComboBox.getValue().getFirstName() + " " + player2ComboBox.getValue().getLastName()
                );
            }
        } else {
            if (team1ComboBox.getValue() != null && team2ComboBox.getValue() != null) {
                matchWinnerComboBox.getItems().addAll(team1ComboBox.getValue().getTeamName(), team2ComboBox.getValue().getTeamName());
            }
        }
    }

    private void showUpdateMatchDialog() {
        ObservableList<Match> allMatches = FXCollections.observableArrayList(matchDAO.getAllMatches());

        ComboBox<Match> matchSelectionComboBox = new ComboBox<>(allMatches);
        matchSelectionComboBox.setPromptText("Select a match to update");

        matchSelectionComboBox.setCellFactory(param -> new ListCell<Match>() {
            @Override
            protected void updateItem(Match match, boolean empty) {
                super.updateItem(match, empty);
                if (empty || match == null) {
                    setText(null);
                } else {
                    if (match.getMatchType().equals("Player vs Player")) {
                        setText(match.getPlayer1().getFirstName() + " " + match.getPlayer1().getLastName() + " vs " +
                                match.getPlayer2().getFirstName() + " " + match.getPlayer2().getLastName());
                    } else {
                        setText(match.getTeam1().getTeamName() + " vs " + match.getTeam2().getTeamName());
                    }
                }
            }
        });

        matchSelectionComboBox.setButtonCell(new ListCell<Match>() {
            @Override
            protected void updateItem(Match match, boolean empty) {
                super.updateItem(match, empty);
                if (empty || match == null) {
                    setText(null);
                } else {
                    if (match.getMatchType().equals("Player vs Player")) {
                        setText(match.getPlayer1().getFirstName() + " " + match.getPlayer1().getLastName() + " vs " +
                                match.getPlayer2().getFirstName() + " " + match.getPlayer2().getLastName());
                    } else {
                        setText(match.getTeam1().getTeamName() + " vs " + match.getTeam2().getTeamName());
                    }
                }
            }
        });

        VBox updateMatchLayout = new VBox(10);
        updateMatchLayout.setPadding(new Insets(20));

        ComboBox<String> matchStatusComboBox = new ComboBox<>();
        matchStatusComboBox.getItems().addAll("Upcoming", "Finished");

        TextField matchResultTextField = new TextField();
        matchResultTextField.setPromptText("Match Result");

        ComboBox<String> matchWinnerComboBox = new ComboBox<>();
        matchWinnerComboBox.setPromptText("Match Winner");

        matchStatusComboBox.setOnAction(event -> {
            String selectedStatus = matchStatusComboBox.getValue();
            boolean isFinished = "Finished".equals(selectedStatus);
            matchResultTextField.setVisible(isFinished);
            matchWinnerComboBox.setVisible(isFinished);
        });

        matchSelectionComboBox.setOnAction(event -> {
            Match selectedMatch = matchSelectionComboBox.getValue();
            if (selectedMatch != null) {
                matchStatusComboBox.setValue(selectedMatch.getMatchStatus());
                matchResultTextField.setText(selectedMatch.getMatchResult());

                matchWinnerComboBox.getItems().clear();
                if (selectedMatch.getMatchType().equals("Player vs Player")) {
                    matchWinnerComboBox.getItems().addAll(
                            selectedMatch.getPlayer1().getFirstName() + " " + selectedMatch.getPlayer1().getLastName(),
                            selectedMatch.getPlayer2().getFirstName() + " " + selectedMatch.getPlayer2().getLastName()
                    );
                } else {
                    matchWinnerComboBox.getItems().addAll(
                            selectedMatch.getTeam1().getTeamName(),
                            selectedMatch.getTeam2().getTeamName()
                    );
                }
                matchWinnerComboBox.setValue(selectedMatch.getMatchWinner());
            }
        });

        Button saveButton = new Button("Save Changes");
        saveButton.setOnAction(event -> {
            String matchResult = matchResultTextField.getText();
            if (!matchResult.matches("\\d+-\\d+")) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter the result in the format x-x.");
                return;
            }

            Match selectedMatch = matchSelectionComboBox.getValue();
            if (selectedMatch != null) {
                try {
                    selectedMatch.setMatchStatus(matchStatusComboBox.getValue());
                    selectedMatch.setMatchResult(matchResult);
                    selectedMatch.setMatchWinner(matchWinnerComboBox.getValue());
                    matchDAO.updateMatch(selectedMatch);
                    matchTableView.setItems(FXCollections.observableArrayList(matchDAO.getAllMatches()));
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Match updated successfully!");
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Something went wrong while updating the match.");
                }
            }
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeWindow(closeButton));

        HBox buttonsLayout = new HBox(10, saveButton, closeButton);
        buttonsLayout.setAlignment(Pos.CENTER);

        updateMatchLayout.getChildren().addAll(
                new Label("Select a match:"), matchSelectionComboBox,
                new Label("Match Status:"), matchStatusComboBox,
                new Label("Result:"), matchResultTextField,
                new Label("Winner:"), matchWinnerComboBox,
                buttonsLayout
        );

        Stage updateMatchStage = new Stage();
        updateMatchStage.setScene(new Scene(updateMatchLayout, 400, 400));
        updateMatchStage.setTitle("Update Match");
        updateMatchStage.show();
    }


    private void showDeleteMatchDialog() {
        ObservableList<Match> allMatches = FXCollections.observableArrayList(matchDAO.getAllMatches());

        ComboBox<Match> matchSelectionComboBox = new ComboBox<>(allMatches);
        matchSelectionComboBox.setPromptText("Select a match to delete");

        matchSelectionComboBox.setCellFactory(param -> new ListCell<Match>() {
            @Override
            protected void updateItem(Match item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getGame().getGameName() + " - " + item.getMatchDate());
                }
            }
        });

        matchSelectionComboBox.setButtonCell(new ListCell<Match>() {
            @Override
            protected void updateItem(Match item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getGame().getGameName() + " - " + item.getMatchDate());
                }
            }
        });

        VBox deleteMatchLayout = new VBox(10);
        deleteMatchLayout.setPadding(new Insets(20));
        deleteMatchLayout.setAlignment(Pos.CENTER);

        Button deleteButton = new Button("Delete Match");
        deleteButton.setOnAction(event -> {
            Match selectedMatch = matchSelectionComboBox.getValue();
            if (selectedMatch != null) {
                try {
                    matchDAO.deleteMatch(selectedMatch.getId());
                    matchTableView.setItems(FXCollections.observableArrayList(matchDAO.getAllMatches()));
                    allMatches.remove(selectedMatch);
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Match deleted successfully!");
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Something went wrong while deleting the match.");
                }
            }
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeWindow(closeButton));

        HBox buttonLayout = new HBox(10);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(deleteButton, closeButton);

        deleteMatchLayout.getChildren().addAll(
                new Label("Select a match to delete:"), matchSelectionComboBox,
                buttonLayout
        );

        Stage deleteMatchStage = new Stage();
        deleteMatchStage.setScene(new Scene(deleteMatchLayout, 400, 200));
        deleteMatchStage.setTitle("Delete Match");
        deleteMatchStage.show();
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Match createNewMatch(String matchType, Game game, Player player1, Player player2, Team team1, Team team2,
                                 LocalDate matchDate, String matchStatus, String matchResult, String matchWinner) {
        Match match = new Match();
        match.setMatchType(matchType);
        match.setGame(game);
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setMatchDate(matchDate);
        match.setMatchStatus(matchStatus);
        match.setMatchResult(matchResult);
        match.setMatchWinner(matchWinner);
        return match;
    }

    private void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    private ObservableList<Player> getPlayersForGame(Game game) {
        ObservableList<Player> playersForGame = FXCollections.observableArrayList();

        if (game != null) {
            String jpql = "SELECT p FROM Player p WHERE p.gameId = :game";
            TypedQuery<Player> query = entityManager.createQuery(jpql, Player.class);
            query.setParameter("game", game);
            List<Player> playerList = query.getResultList();
            playersForGame.addAll(playerList);
        }

        return playersForGame;
    }

    private ObservableList<Team> getTeamsForGame(Game game) {
        ObservableList<Team> teamsForGame = FXCollections.observableArrayList();

        if (game != null) {
            String jpql = "SELECT t FROM Team t WHERE t.game = :game";
            TypedQuery<Team> query = entityManager.createQuery(jpql, Team.class);
            query.setParameter("game", game);
            List<Team> teamList = query.getResultList();
            teamsForGame.addAll(teamList);
        }

        return teamsForGame;
    }

    private ObservableList<Player> getAllPlayers() {
        return FXCollections.observableArrayList(PlayerDAO.getAllPlayers());
    }

    private ObservableList<Team> getAllTeams() {
        return FXCollections.observableArrayList(TeamDAO.getAllTeams());
    }

    private ObservableList<Game> getAllGames() {
        return FXCollections.observableArrayList(GameDAO.getAllGames());
    }
}