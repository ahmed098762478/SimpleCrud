package com.example.project1.dto;

public class StoreDTO {
    private int id;
    private String nameStore;


    public StoreDTO(int id, String nameStore) {
        this.id = id;
        this.nameStore = nameStore;
    }

    public int getId() {
        return id;
    }
    public String getNameStore() {
        return nameStore;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

}
