package org.benz_forza.projectbenzforza.DAO;
import org.benz_forza.projectbenzforza.entities.Staff;
import jakarta.persistence.*;
import java.util.List;
//Johan
public class StaffDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("myconfig");

    public void create(Staff staff) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(staff);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println("Error creating staff: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Staff findById(long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Staff.class, id);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid arguments provided for findById: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public static List<Staff> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("FROM Staff", Staff.class).getResultList();
        } catch (PersistenceException e) {
            System.err.println("Error retrieving all staff: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void update(Staff staff) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(staff);
            em.getTransaction().commit();
        } catch (OptimisticLockException e) {
            System.err.println("Update failed due to concurrency issues: " + e.getMessage());
            em.getTransaction().rollback();
        } catch (PersistenceException e) {
            System.err.println("Error updating staff: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void delete(long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            Staff staff = em.find(Staff.class, id);
            if (staff != null) {
                em.remove(staff);
            } else {
                System.err.println("Staff with id " + id + " not found");
            }
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println("Error deleting staff: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
