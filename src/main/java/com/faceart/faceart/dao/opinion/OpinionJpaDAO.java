package com.faceart.faceart.dao.opinion;

import com.faceart.faceart.entities.Opinion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

import java.util.List;

public class OpinionJpaDAO implements OpinionDAO {

    @PersistenceUnit(name = "default")
    private final EntityManager em;

    public OpinionJpaDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    @Override
    public Opinion getOpinionById(long id) {
        return em.find(Opinion.class, id);
    }

    public List getAll() {
        return em.createQuery("SELECT o FROM Opinion o")
                .getResultList();
    }

    public void remove(Opinion opinion) {
        em.getTransaction().begin();
        em.remove(opinion);
        em.getTransaction().commit();
    }

    public Opinion persist(Opinion opinion) {
        em.persist(opinion);
        return opinion;
    }

    @Override
    public void save(Opinion opinion) {
        em.getTransaction().begin();
        em.merge(opinion);
        em.getTransaction().commit();
    }
}
