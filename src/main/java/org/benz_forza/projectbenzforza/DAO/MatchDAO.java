package org.benz_forza.projectbenzforza.DAO;

import jakarta.persistence.*;
import org.benz_forza.projectbenzforza.entities.Match;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;

import java.util.List;

public class MatchDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("myconfig");

    public Match createPlayerVsPlayerMatch(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Both players must be provided for a Player vs Player match.");
        }

        Match match = new Match();
        match.setMatchType(Match.MatchType.PLAYER_VS_PLAYER);
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setTeam1(null);
        match.setTeam2(null);

        match.validateMatch();

        return save(match);
    }

    public Match createTeamVsTeamMatch(Team team1, Team team2) {
        if (team1 == null || team2 == null) {
            throw new IllegalArgumentException("Both teams must be provided for a Team vs Team match.");
        }

        Match match = new Match();
        match.setMatchType(Match.MatchType.TEAM_VS_TEAM);
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setPlayer1(null);
        match.setPlayer2(null);

        match.validateMatch();

        return save(match);
    }

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
        try {
            TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m", Match.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Match> findMatchesByType(Match.MatchType matchType) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m WHERE m.matchType = :matchType", Match.class);
            query.setParameter("matchType", matchType);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Match> findFinishedMatches() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m WHERE m.isFinished = true", Match.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Match> findUpcomingMatches() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m WHERE m.isFinished = false", Match.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
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
