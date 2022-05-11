package com.faceart.faceart.entities;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ElementCollection
    private ArrayList<String> roles;

    @OneToMany( targetEntity=Product.class )
    private ArrayList<Product> products;


    public User(){}

    public User(
            String firstName,
            String secondName,
            String email,
            String password,
            String address,
            boolean active
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.active = active;
        this.roles = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.firstName+' '+this.secondName;
    }

    public ArrayList<String> getRoles()
    {
        return roles;
    }

    public void addRole(String role) {
        roles.add(role);
    }

    public void deleteRole(String role) {
        roles.remove(role);
    }

    public ArrayList<Product> getProducts()
    {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public boolean hasRole(String role)
    {
        return roles.contains(role);
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
