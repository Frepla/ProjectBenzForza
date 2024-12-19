
package org.benz_forza.projectbenzforza.DAO;

import jakarta.persistence.*;
import org.benz_forza.projectbenzforza.entities.Game;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;

import java.util.ArrayList;
import java.util.List;
//JESPER
public class PlayerDAO {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myconfig");

    public static boolean addPlayer(String firstName, String lastName, String nickName, String address, String zipCode, String city,
                                    String country, String email){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Player player = new Player();
            player.setFirstName(firstName);
            player.setLastName(lastName);
            player.setNickName(nickName);
            player.setAddress(address);
            player.setZipCode(zipCode);
            player.setCity(city);
            player.setCountry(country);
            player.setEmail(email);

            entityManager.persist(player);
            transaction.commit();

            System.out.println(player.getId() + player.getFirstName());

            System.out.println("Player saved to database: " + player);
            return true;


        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                System.out.println("Error: Duplicate value for nickname or email.");
            } else {
                System.out.println("Error: Could not save player. " + e.getMessage());
            }
            return false;
        }


    }

    public static boolean updatePlayerTeam(int playerId, int newTeamId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Player player = entityManager.find(Player.class, playerId);
            if (player != null) {
                if (newTeamId == -1) {
                    player.setTeamId(null);
                } else {
                    Team newTeam = entityManager.find(Team.class, newTeamId);
                    if (newTeam != null) {
                        player.setTeamId(newTeam);
                    } else {
                        System.out.println("Error: Team with ID " + newTeamId + " not found.");
                        return false;
                    }
                }
                entityManager.merge(player);
                transaction.commit();
                System.out.println("Player " + playerId + " updated with new team");
                return true;
            } else {
                System.out.println("Error: Player with ID " + playerId + " not found.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            entityManager.close();
        }
    }


    public static boolean updatePlayerGame(int playerId, int newGameId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Player player = entityManager.find(Player.class, playerId);
            if (player != null) {
                Game newGame = entityManager.find(Game.class, newGameId);
                if (newGame != null) {
                    player.setGameId(newGame);
                    entityManager.merge(player);
                    transaction.commit();
                    System.out.println("Player " + playerId + " updated to new Game");
                    return true;
                } else {
                    System.out.println("Error: Team with ID " + newGameId + " not found.");
                    return false;
                }
            } else {
                System.out.println("Error: Player with ID " + playerId + " not found.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            entityManager.close();
        }
    }

    public static boolean deletePlayer(Player player) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (!entityManager.contains(player)) {
                player = entityManager.merge(player);
                System.out.println("Merging player: " + player.getId() + " " + player.getFirstName());
            }
            entityManager.remove(player);
            transaction.commit();
            System.out.println("Player " + player.getId() + " deleted successfully.");
            return true;

        } catch (Exception e) {
            System.out.println("Error: Can't delete player: " + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;

        } finally {
            entityManager.close();
        }
    }


    public static List<Player> getAllPlayers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Player> listToReturn = new ArrayList<>();
        try {
            TypedQuery<Player> result = entityManager.createQuery("FROM Player", Player.class);
            listToReturn.addAll(result.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return listToReturn;
    }
    public static List<Team> getAllTeams() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Team> teamList = new ArrayList<>();
        try {
            TypedQuery<Team> result = entityManager.createQuery("FROM Team", Team.class);
            teamList.addAll(result.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return teamList;
    }
    public static Team getTeamById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Team teamToReturn = null;
        try {
            teamToReturn = entityManager.find(Team.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return teamToReturn;
    }

    public static String getTeamNameById(int id) {
        Team team = getTeamById(id);
        return (team != null) ? team.getTeamName() : null;
    }
    public static List<Game> getAllGames() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Game> gameList = new ArrayList<>();
        try {
            TypedQuery<Game> result = entityManager.createQuery("FROM Game", Game.class);
            gameList.addAll(result.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return gameList;
    }
    public static Game getGameById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Game gameToReturn = null;
        try {
            gameToReturn = entityManager.find(Game.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return gameToReturn;
    }

    public static String getGameNameById(int id) {
        Game game = getGameById(id);
        if (game != null) {
            return game.getGameName();
        } else {
            return null;
        }
    }



    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}