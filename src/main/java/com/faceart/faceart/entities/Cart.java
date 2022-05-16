package com.faceart.faceart.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToMany
    private List<Product> products;

    @OneToOne
    private User user;

    public Cart(){}

    public Cart(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (!this.products.contains(product)) {
            this.products.add(product);
        }
    }

    public void removeProduct(Long productId) {
        this.products.removeIf(e -> e.getId() == productId);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
