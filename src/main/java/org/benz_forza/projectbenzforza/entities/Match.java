package org.benz_forza.projectbenzforza.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_type", nullable = false)
    private MatchType matchType;

    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = true)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = true)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = true)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = true)
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "winner_player_id")
    private Player winnerPlayer;

    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime matchDate;

    @Column(name = "is_finished", nullable = false)
    private boolean isFinished;

    @Column(name = "result")
    private String result;

    public enum MatchType {
        PLAYER_VS_PLAYER,
        TEAM_VS_TEAM
    }

    @PrePersist
    @PreUpdate
    public void validateMatch() {
        if (matchType == MatchType.PLAYER_VS_PLAYER) {
            if (player1 == null || player2 == null) {
                throw new IllegalStateException("Player1 and Player2 must be set for PLAYER_VS_PLAYER matches.");
            }
            if (team1 != null || team2 != null) {
                throw new IllegalStateException("Team1 and Team2 must be null for PLAYER_VS_PLAYER matches.");
            }
        } else if (matchType == MatchType.TEAM_VS_TEAM) {
            if (team1 == null || team2 == null) {
                throw new IllegalStateException("Team1 and Team2 must be set for TEAM_VS_TEAM matches.");
            }
            if (player1 != null || player2 != null) {
                throw new IllegalStateException("Player1 and Player2 must be null for TEAM_VS_TEAM matches.");
            }
        }
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
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

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setWinnerPlayer(Player winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", matchType=" + matchType +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", winnerPlayer=" + winnerPlayer +
                ", winnerTeam=" + winnerTeam +
                ", game=" + game +
                ", matchDate=" + matchDate +
                ", isFinished=" + isFinished +
                ", result='" + result + '\'' +
                '}';
    }
}
