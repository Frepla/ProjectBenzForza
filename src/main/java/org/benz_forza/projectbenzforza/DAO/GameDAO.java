package org.benz_forza.projectbenzforza.DAO;
import jakarta.persistence.*;
import org.benz_forza.projectbenzforza.entities.Game;

import java.util.List;
import java.util.ArrayList;

public class GameDAO {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("myconfig");

    public static boolean saveGame(String gameName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Game game = new Game();
            game.setGameName(gameName);

            entityManager.persist(game);
            transaction.commit();

            System.out.println("Game saved to database: " + game);
            return true;

        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                System.out.println("Error: Duplicate game name.");
            } else {
                System.out.println("Error: Could not save game. " + e.getMessage());
            }
            return false;

        } finally {
            entityManager.close();
        }
    }

    public static Game getGameById(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return entityManager.find(Game.class, id);
        } finally {
            entityManager.close();
        }
    }

    public static List<Game> getAllGames() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Game> games = new ArrayList<>();
        try {
            TypedQuery<Game> query = entityManager.createQuery("FROM Game", Game.class);
            games.addAll(query.getResultList());
            return games;
        } finally {
            entityManager.close();
        }
    }

    public static boolean updateGame(Game gameToUpdate) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(gameToUpdate);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error: Could not update game. " + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            entityManager.close();
        }
    }

    public static boolean deleteGameById(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Game gameToDelete = entityManager.find(Game.class, id);
            if (gameToDelete != null) {
                entityManager.remove(entityManager.contains(gameToDelete) ? gameToDelete : entityManager.merge(gameToDelete));
                transaction.commit();
                System.out.println("Game deleted successfully.");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error: Could not delete game. " + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            entityManager.close();
        }
    }

    public static void close() {
        if (ENTITY_MANAGER_FACTORY != null) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}


