package com.faceart.faceart.dao.cart;

import com.faceart.faceart.entities.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

import java.util.List;

public class CartJpaDAO implements CartDAO {

    @PersistenceUnit(name = "default")
    private final EntityManager em;

    public CartJpaDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    @Override
    public Cart getFavouriteById(long id) {
        return em.find(Cart.class, id);
    }

    public List getAll() {
        return em.createQuery("SELECT f FROM Cart f")
                .getResultList();
    }

    public void remove(Cart cart) {
        em.getTransaction().begin();
        em.remove(cart);
        em.getTransaction().commit();
    }

    public Cart persist(Cart cart) {
        em.persist(cart);
        return cart;
    }

    @Override
    public void save(Cart cart) {
        em.getTransaction().begin();
        em.merge(cart);
        em.getTransaction().commit();
    }
}
