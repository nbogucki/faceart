package com.faceart.faceart.dao.product;

import com.faceart.faceart.entities.Product;
import com.faceart.faceart.entities.User;

import java.util.List;

public interface ProductDAO {

    Product getProductById(long id);

    List getAll();

    List getProductsForIndex();

    void save(Product product);

    List getProductsByUser(User user);

    void remove(Product product);
}
