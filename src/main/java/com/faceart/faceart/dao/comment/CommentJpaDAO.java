package com.faceart.faceart.dao.comment;

import com.faceart.faceart.entities.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

import java.util.List;

public class CommentJpaDAO implements CommentDAO {

    @PersistenceUnit(name = "default")
    private final EntityManager em;

    public CommentJpaDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    @Override
    public Comment getCommentById(long id) {
        return em.find(Comment.class, id);
    }

    public List getAll() {
        return em.createQuery("SELECT c FROM Comment c")
                .getResultList();
    }

    public void remove(Comment comment) {
        em.getTransaction().begin();
        em.remove(comment);
        em.getTransaction().commit();
    }

    public Comment persist(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public void save(Comment comment) {
        em.getTransaction().begin();
        em.merge(comment);
        em.getTransaction().commit();
    }
}
