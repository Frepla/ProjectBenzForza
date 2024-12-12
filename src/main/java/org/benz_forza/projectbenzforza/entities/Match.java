package org.benz_forza.projectbenzforza.entities;

import jakarta.persistence.*;
import org.hibernate.mapping.Join;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = true)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = true)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = true)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = true)
    private Team team2;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime matchDate;

    @Column(name = "is_finished", nullable = false)
    private boolean isFinished;

    @Column(name = "result")
    private String result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
}
