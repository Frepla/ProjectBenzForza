package org.benz_forza.projectbenzforza.entities;

import jakarta.persistence.*;

@Entity
@Table(name="player")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int id;

    @Column(name ="player_first_name",length = 50, nullable = false)
    private String firstName;

    @Column(name ="player_nick_name",length = 50, nullable = false)
    private String nickName;

    @Column(name ="player_last_name",length = 50, nullable = false)
    private String lastName;

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    @ManyToOne

    @JoinColumn(name = "team_id", nullable = true)
    private Team teamId;




    public Player() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
