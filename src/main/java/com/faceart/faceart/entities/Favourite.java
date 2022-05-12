package com.faceart.faceart.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "favourites")
public class Favourite {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    private List<Product> products;

    public Favourite(){}

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
}
