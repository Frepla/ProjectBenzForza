package org.benz_forza.projectbenzforza.DAO;

import org.benz_forza.projectbenzforza.entities.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class MatchDAO {
    private EntityManager entityManager;

    public MatchDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Match save(Match match) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(match);
            transaction.commit();
            return match;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Failed to save match", e);
        }
    }

    public Match findById(int id) {
        return entityManager.find(Match.class, id);
    }

    public List<Match> findAll() {
        return entityManager.createQuery("SELECT m FROM Match m", Match.class).getResultList();
    }

    public void delete(Match match) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(match) ? match : entityManager.merge(match));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Failed to delete match", e);
        }
    }
}
