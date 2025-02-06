package com.rafalesantos.blog.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_publication")
public class Publication implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;
    public String content;
    public String category;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;


    public Publication (){

    }

    public Publication(Long id, String title, String content, String category, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
