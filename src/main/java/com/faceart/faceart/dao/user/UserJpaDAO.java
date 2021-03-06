package com.faceart.faceart.dao.user;

import com.faceart.faceart.entities.Product;
import com.faceart.faceart.entities.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;

import java.util.List;

@RequestScoped
public class UserJpaDAO implements UserDAO{

    @PersistenceUnit(name = "default")
    private final EntityManager em;

    public UserJpaDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    @Override
    public User getUserById(long id) {
        try{
            return em.find(User.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getUserByEmailAndPassword(String email, String password) {
        try {
            return em.createQuery("SELECT u FROM User u " +
                            "WHERE u.email = :email AND" +
                            " u.password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        return (User) em.createQuery("SELECT u FROM User u " +
                        "WHERE u.email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    public List getAll() {
        return em.createQuery("SELECT u FROM User u")
                .getResultList();
    }

    public void remove(User user) {
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    public void save(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }
}
