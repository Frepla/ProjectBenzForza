package org.benz_forza.projectbenzforza.entities;

import jakarta.persistence.*;
//JESPER
@Entity
@Table(name="player", uniqueConstraints = {@UniqueConstraint(columnNames = {"player_nick_name"}),
        @UniqueConstraint(columnNames = {"player_email"})
})

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int id;


    @Column(name ="player_first_name",length = 50, nullable = false)
    private String firstName;

    @Column(name ="player_nick_name",length = 50, nullable = false,unique = true)
    private String nickName;

    @Column(name ="player_last_name",length = 50, nullable = false)
    private String lastName;

    @Column(name ="player_address",length = 50, nullable = false)
    private String address ;

    @Column(name ="player_zip_code",length = 50, nullable = false)
    private String zipCode ;

    @Column(name ="player_city",length = 50, nullable = false)
    private String city;

    @Column(name ="player_country",length = 50, nullable = false)
    private String country;

    @Column(name ="player_email",length = 50, nullable = false, unique = true)
    private String email;


    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team teamId;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = true)
    private Game gameId;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        String teamName;
        if (teamId != null) {
            teamName = teamId.getTeamName();
        } else {
            teamName = "No Team";
        }

        String gameName;
        if (gameId != null) {
            gameName = gameId.getGameName();
        } else {
            gameName = "No Game";
        }

        return
                firstName + " " + "\"" + nickName + "\"" + " " + lastName + " | " + teamName + " | " + gameName;
    }


}




//    @Override
//    public String toString() {
//        return "Player{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", nickName='" + nickName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", address='" + address + '\'' +
//                ", zipCode='" + zipCode + '\'' +
//                ", city='" + city + '\'' +
//                ", country='" + country + '\'' +
//                ", email='" + email + '\'' +
//                ", teamId=" + teamId +
//                ", gameId=" + gameId +
//                '}';
//    }