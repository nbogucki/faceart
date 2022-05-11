package com.faceart.faceart.dao.product;

import com.faceart.faceart.entities.Product;
import com.faceart.faceart.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import java.util.List;

public class ProductJpaDAO implements ProductDAO{

    @PersistenceUnit(name = "default")
    private final EntityManager em;

    public ProductJpaDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    @Override
    public Product getProductById(long id) {
        return em.find(Product.class, id);
    }

    public List getAll() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.createdAt DESC")
                .getResultList();
    }

    public List getProductsForIndex() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.createdAt DESC")
                .setMaxResults(8)
                .getResultList();
    }

    public List getProductsByUser(User user) {
        return em.createQuery("SELECT p FROM Product p WHERE p.user = :user ORDER BY p.createdAt DESC")
                .setParameter("user", user)
                .setMaxResults(8)
                .getResultList();
    }

    public void remove(Product product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    @Override
    public void save(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }
}
