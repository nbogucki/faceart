package com.faceart.faceart.dao.favourite;

import com.faceart.faceart.entities.Favourite;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

import java.util.List;

public class FavouriteJpaDAO implements FavouriteDAO {

    @PersistenceUnit(name = "default")
    private final EntityManager em;

    public FavouriteJpaDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    @Override
    public Favourite getFavouriteById(long id) {
        return em.find(Favourite.class, id);
    }

    public List getAll() {
        return em.createQuery("SELECT f FROM Favourite f")
                .getResultList();
    }

    public void remove(Favourite favourite) {
        em.getTransaction().begin();
        em.remove(favourite);
        em.getTransaction().commit();
    }

    public Favourite persist(Favourite favourite) {
        em.persist(favourite);
        return favourite;
    }

    @Override
    public void save(Favourite favourite) {
        em.getTransaction().begin();
        em.merge(favourite);
        em.getTransaction().commit();
    }
}
