package org.benz_forza.projectbenzforza.DAO;

import jakarta.persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.benz_forza.projectbenzforza.entities.Match;

// Fredrik
public class MatchDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myconfig");

    public MatchDAO() {
    }

    public void addMatch(Match match) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(match);
            em.getTransaction().commit();
        }
    }

    public ObservableList<Match> getAllMatches() {
        try (EntityManager em = emf.createEntityManager()) {
            return FXCollections.observableArrayList(
                    em.createQuery("SELECT m FROM Match m", Match.class).getResultList()
            );
        }
    }

    public ObservableList<Match> getUpcomingMatches() {
        try (EntityManager em = emf.createEntityManager()) {
            return FXCollections.observableArrayList(
                    em.createQuery("SELECT m FROM Match m WHERE m.matchStatus = 'Upcoming'", Match.class).getResultList()
            );
        }
    }

    public void updateMatch(Match match) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(match);
            em.getTransaction().commit();
        }
    }

    public void deleteMatch(int matchId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Match match = em.find(Match.class, matchId);
            if (match != null) {
                em.remove(match);
            }
            em.getTransaction().commit();
        }
    }
}
