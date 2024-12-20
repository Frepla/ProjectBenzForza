package org.benz_forza.projectbenzforza.DAO;
import org.benz_forza.projectbenzforza.entities.Staff;
import jakarta.persistence.*;
import java.util.List;

public class StaffDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("myconfig");
    public void create(Staff staff) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(staff);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Staff findById(long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Staff.class, id);
        } finally {
            em.close();
        }
    }

    public List<Staff> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("FROM Staff", Staff.class).getResultList();
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
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
