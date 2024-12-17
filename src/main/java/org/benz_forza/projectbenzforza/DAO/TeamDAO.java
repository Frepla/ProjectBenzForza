package org.benz_forza.projectbenzforza.DAO;

import jakarta.persistence.*;
import org.benz_forza.projectbenzforza.entities.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("projectBenzForzaPU");

    // Create
    public boolean saveTeam(Team team) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(team);
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

    // Read One by ID
    public Team getTeamById(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return entityManager.find(Team.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Team> getAllTeams() {
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

    public boolean updateTeam(Team teamToUpdate) {
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

    public boolean deleteTeam(Team team) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            if (!entityManager.contains(team)) {
                team = entityManager.merge(team);
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

    public boolean deleteTeamById(int id) {
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
}
