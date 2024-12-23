package org.benz_forza.projectbenzforza.entities;

import jakarta.persistence.*;
// Denise
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int id;

    @Column(name = "game_name",nullable = false, unique = true)
    private String gameName;

    @OneToMany(mappedBy = "gameId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teams = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }


    public void addPlayer(Player player) {
        players.add(player);
        player.setGameId(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.setGameId(null);
    }

    public void addTeam(Team team) {
        teams.add(team);
        team.setGame(this);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
        team.setGame(null);
    }
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
