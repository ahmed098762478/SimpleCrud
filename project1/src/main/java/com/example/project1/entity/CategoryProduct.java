package com.example.project1.entity;


import jakarta.persistence.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Table(name="category_product")
public class CategoryProduct {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name="name_category")
    private String nameCategory;

    public int getId() {
        return id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
