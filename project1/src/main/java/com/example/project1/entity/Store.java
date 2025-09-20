package com.example.project1.entity;


import jakarta.persistence.*;

@Entity
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name_store")
    private String nameStore;


    public int getId(){
        return id;
    }

    public String getNameStore(){
        return nameStore;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setNameStore(String nameStore){
        this.nameStore=nameStore;
    }


}
