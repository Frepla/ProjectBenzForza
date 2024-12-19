package org.benz_forza.projectbenzforza.DAO;

import jakarta.persistence.*;
import org.benz_forza.projectbenzforza.entities.Game;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;

import java.util.ArrayList;
import java.util.List;
// FREDRIK & JESPER
public class TeamDAO {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("myconfig");


    public static boolean saveTeam(String teamName, Game game) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();


            Team team = new Team();
            team.setTeamName(teamName);
            team.setGame(game);

            entityManager.persist(team);
            transaction.commit();

            System.out.println("Team saved to database: " + team);
            return true;

        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                System.out.println("Error: Duplicate team name.");
            } else {
                System.out.println("Error: Could not save team. " + e.getMessage());
            }
            return false;

        } finally {
            entityManager.close();
        }
    }



    public Team getTeamById(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return entityManager.find(Team.class, id);
        } finally {
            entityManager.close();
        }
    }

    public static List<Team> getAllTeams() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Team> teams = new ArrayList<>();
        try {
            TypedQuery<Team> query = entityManager.createQuery("FROM Team", Team.class);
            teams.addAll(query.getResultList());
            return teams;
        } finally {
            entityManager.close();
        }
    }

    public static boolean updateTeam(Team teamToUpdate) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entityManager.contains(teamToUpdate) ? teamToUpdate : entityManager.merge(teamToUpdate));
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (entityManager != null && transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            entityManager.close();
        }
    }

    public static boolean deleteTeam(Team team) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (!entityManager.contains(team)) {
                team = entityManager.merge(team);
            }

            List<Player> playersInTeam = entityManager.createQuery(
                            "SELECT p FROM Player p WHERE p.teamId = :team", Player.class)
                    .setParameter("team", team)
                    .getResultList();

            if (!playersInTeam.isEmpty()) {
                for (Player player : playersInTeam) {
                    removePlayer(player);
                }
            }
            entityManager.remove(team);

            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (entityManager != null && transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            entityManager.close();
        }
    }



    public static boolean deleteTeamById(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Team teamToDelete = entityManager.find(Team.class, id);
            if (teamToDelete != null) {
                entityManager.remove(entityManager.contains(teamToDelete) ? teamToDelete : entityManager.merge(teamToDelete));
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (entityManager != null && transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            entityManager.close();
        }
    }
    public static Game getGameById(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
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

    public static boolean addPlayer(Player player, Team team) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            player.setTeamId(team);

            entityManager.persist(entityManager.contains(player) ? player : entityManager.merge(player));

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;

        } finally {
            entityManager.close();
        }
    }

    public static boolean removePlayer(Player player) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            player.setTeamId(null);
            entityManager.merge(player);

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;

        } finally {
            entityManager.close();
        }
    }


}



