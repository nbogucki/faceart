package com.faceart.faceart.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    private User user;

    @ElementCollection
    private ArrayList<String> images;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "products")
    private List<Favourite> favourites;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    public Product(){}

    public Product(
            String name,
            float price,
            String title,
            User user,
            LocalDateTime createdAt
    ) {
        this.name = name;
        this.price = price;
        this.title = title;
        this.user = user;
        this.createdAt = createdAt;
        this.images = new ArrayList<>();
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImages()
    {
        return images;
    }

    public void addImage(String image) {
        images.add(image);
    }

    public void deleteImage(String image) {
        images.remove(image);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void addFavourite(Favourite favourite) {
        if (!this.favourites.contains(favourite)) {
            this.favourites.add(favourite);
        }
    }

    public void removeFavourite(Long favouriteId) {
        this.favourites.removeIf(e -> e.getId() == favouriteId);
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void addCart(Cart cart) {
        if (!this.carts.contains(cart)) {
            this.carts.add(cart);
        }
    }

    public void removeCart(Long cartId) {
        this.carts.removeIf(e -> e.getId() == cartId);
    }
}
