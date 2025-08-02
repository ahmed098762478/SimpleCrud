package com.example.project1.entity;

import jakarta.persistence.*;


@Entity
@Table(name="supplier")
public class Supplier {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
@Column(name="supplier_name")
private String name;

@Column(name="mail_supplier")
    private String mail;

@Column(name="adress")
    private String adress;
@Column(name="phone")
    private String phone;

public int getId(){
    return id;
}
public String getName(){
    return name;
}
public String getMail(){
    return mail;
}

public String getAdress(){
    return adress;
}

    public String getPhone(){
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




}

