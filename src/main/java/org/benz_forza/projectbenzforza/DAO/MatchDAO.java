package org.benz_forza.projectbenzforza.DAO;

import jakarta.persistence.*;
import org.benz_forza.projectbenzforza.entities.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("projectBenzForzaPU");

    public Match save(Match match) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(match);
            transaction.commit();
            return match;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save match", e);
        } finally {
            entityManager.close();
        }
    }

    public Match findById(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return entityManager.find(Match.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Match> findAll() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Match> matches = new ArrayList<>();
        try {
            TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m", Match.class);
            matches.addAll(query.getResultList());
        } finally {
            entityManager.close();
        }
        return matches;
    }

    public List<Match> findMatchesByType(Match.MatchType matchType) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Match> matches = new ArrayList<>();
        try {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m WHERE m.matchType = :matchType", Match.class);
            query.setParameter("matchType", matchType);
            matches.addAll(query.getResultList());
        } finally {
            entityManager.close();
        }
        return matches;
    }

    public List<Match> findFinishedMatches() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Match> matches = new ArrayList<>();
        try {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m WHERE m.isFinished = true", Match.class);
            matches.addAll(query.getResultList());
        } finally {
            entityManager.close();
        }
        return matches;
    }

    public List<Match> findUpcomingMatches() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Match> matches = new ArrayList<>();
        try {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m WHERE m.isFinished = false", Match.class);
            matches.addAll(query.getResultList());
        } finally {
            entityManager.close();
        }
        return matches;
    }

    public boolean delete(Match match) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            if (!entityManager.contains(match)) {
                match = entityManager.merge(match);
            }
            entityManager.remove(match);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete match", e);
        } finally {
            entityManager.close();
        }
    }

    public static void closeFactory() {
        if (ENTITY_MANAGER_FACTORY != null && ENTITY_MANAGER_FACTORY.isOpen()) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}