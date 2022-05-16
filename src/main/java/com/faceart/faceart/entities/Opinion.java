package com.faceart.faceart.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "opinions")
public class Opinion {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="value", nullable = false)
    private int value;

    @Column(name="author_id", nullable = false)
    private Long authorId;

    @ManyToOne
    private User user;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    public Opinion(){}

    public Opinion(Long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Long getAuthor() {
        return authorId;
    }

    public void setAuthor(Long authorId) {
        this.authorId = authorId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
