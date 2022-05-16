package com.faceart.faceart.dao.cart;

import com.faceart.faceart.entities.Cart;

import java.util.List;

public interface CartDAO {

    Cart getFavouriteById(long id);

    List getAll();

    void save(Cart cart);

    void remove(Cart cart);
}
