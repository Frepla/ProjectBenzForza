package org.benz_forza.projectbenzforza.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

// Fredrik
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn (name = "player1_id")
    private Player player1;

    @ManyToOne
    @JoinColumn (name = "player2_id")
    private Player player2;

    @ManyToOne
    @JoinColumn (name = "team1_id")
    private Team  team1;

    @ManyToOne
    @JoinColumn (name = "team2_id")
    private Team team2;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name = "match_type", nullable = false)
    private String matchType;

    @Column (name = "match_status", nullable = false)
    private String matchStatus;

    @Column (name = "match_result")
    private String matchResult;

    @Column (name = "match_winner")
    private String matchWinner;

    public Match(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    public String getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", game=" + game +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", matchDate=" + matchDate +
                ", matchType='" + matchType + '\'' +
                ", matchStatus='" + matchStatus + '\'' +
                ", matchResult='" + matchResult + '\'' +
                '}';
    }
}