package org.benz_forza.projectbenzforza.DAO;

import jakarta.persistence.*;
import org.benz_forza.projectbenzforza.entities.Match;
import org.benz_forza.projectbenzforza.entities.Player;
import org.benz_forza.projectbenzforza.entities.Team;
import org.benz_forza.projectbenzforza.entities.Game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Fredrik
public class MatchDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("myconfig");

    public static Match createPlayerVsPlayerMatch(Player player1, Player player2, Game game, LocalDateTime matchDateTime) {
        validateMatchInputs(player1, player2, game);
        Match match = new Match();
        match.setMatchType(Match.MatchType.PLAYER_VS_PLAYER);
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setGame(game);
        match.setMatchDateFromString(matchDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))); // Corrected method
        match.setFinished(false);
        match.validateMatch();
        return save(match);
    }

    public static Match createTeamVsTeamMatch(Team team1, Team team2, Game game, LocalDateTime matchDateTime) {
        validateMatchInputs(team1, team2, game);
        Match match = new Match();
        match.setMatchType(Match.MatchType.TEAM_VS_TEAM);
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setGame(game);
        match.setMatchDateFromString(matchDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))); // Corrected method
        match.setFinished(false);
        match.validateMatch();
        return save(match);
    }

    private static void validateMatchInputs(Object participant1, Object participant2, Game game) {
        if (participant1 == null || participant2 == null || game == null) {
            throw new IllegalArgumentException("Both participants and the game must be provided.");
        }
    }

    public static Match save(Match match) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(match);
            transaction.commit();
            return match;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save match", e);
        } finally {
            entityManager.close();
        }
    }

    public static List<Match> findAll() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m", Match.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public static List<Match> findFinishedMatches(boolean isFinished) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m WHERE m.isFinished = :isFinished", Match.class);
            query.setParameter("isFinished", isFinished);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public static List<Match> findUpcomingMatches() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m WHERE m.matchDate > :currentDate", Match.class);
            query.setParameter("currentDate", LocalDateTime.now());
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public static boolean delete(Match match) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            match = entityManager.contains(match) ? match : entityManager.merge(match);
            entityManager.remove(match);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete match", e);
        } finally {
            entityManager.close();
        }
    }
}
