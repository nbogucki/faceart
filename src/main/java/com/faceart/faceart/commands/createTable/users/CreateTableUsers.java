package com.faceart.faceart.commands.createTable.users;

import com.faceart.faceart.entities.User;
import com.faceart.faceart.commands.createTable.CreateTable;
import com.faceart.faceart.dao.cart.CartJpaDAO;
import com.faceart.faceart.entities.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CreateTableUsers implements CreateTable {
    public void createTable()
    {
        User user = new User(
                1L,
                "admin_first_name",
                "admin_second_name",
                "admin@admin.com",
                "adminPassword123",
                "admin_address",
                true
                );

        user.addRole("ROLE_ADMIN");
        Cart cart = new Cart(1L);
        CartJpaDAO cartJpaDAO = new CartJpaDAO();
        cartJpaDAO.save(cart);
        user.setCart(cart);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        cart.setUser(user);
        cartJpaDAO.save(cart);
    }
}
