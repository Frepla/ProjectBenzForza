package org.benz_forza.projectbenzforza.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_name", nullable = false, unique = true, length = 100)
    private String teamName;

    @OneToMany(mappedBy = "teamId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public Team() {
    }

    public Team(String teamName, Game game) {
        if (teamName == null || teamName.isEmpty()) {
            throw new IllegalArgumentException("Team name must not be null or empty.");
        }
        this.teamName = teamName;
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        if (teamName == null || teamName.isEmpty()) {
            throw new IllegalArgumentException("Team name must not be null or empty.");
        }
        this.teamName = teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", game=" + (game != null ? game.getGameName() : "N/A") +
                '}';
    }
}
