package org.benz_forza.projectbenzforza.DAO;
import jakarta.persistence.*;
import org.benz_forza.projectbenzforza.entities.Game;

import java.util.List;
import java.util.ArrayList;

public class GameDAO {
private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myconfig");

private static boolean addGame(String gameName){
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = null;

    try {
        transaction = entityManager.getTransaction();
        transaction.begin();
        Game game = new Game();
        game.setGameName(gameName);
        entityManager.persist(game);
        transaction.commit();

        System.out.println("Game added: " + game);
        return true;
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
        return false;
    } finally {
        entityManager.close();
    }
}

    public static Game getGameById(int gameId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Game.class, gameId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static List<Game> getAllGames() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Game> gameList = new ArrayList<>();
        try {
            TypedQuery<Game> query = entityManager.createQuery("FROM Game", Game.class);
            gameList.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return gameList;
    }

    public static boolean updateGameName(int gameId, String newGameName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Game game = entityManager.find(Game.class, gameId);
            if (game != null) {
                game.setGameName(newGameName);
                entityManager.merge(game);
                transaction.commit();
                System.out.println("Game updated: " + game);
                return true;
            } else {
                System.out.println("Game with ID " + gameId + " not found.");
                return false;
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    public static boolean deleteGame(int gameId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Game game = entityManager.find(Game.class, gameId);
            if (game != null) {
                entityManager.remove(game);
                transaction.commit();
                System.out.println("Game deleted: " + game);
                return true;
            } else {
                System.out.println("Game with ID " + gameId + " not found.");
                return false;
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}


