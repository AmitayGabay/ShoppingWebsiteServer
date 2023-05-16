package com.example.ShoppingWebsiteServer.model;

public class IdRequest {
    private Integer id;
    private String name;

    public IdRequest(Integer id, String name) {
        this.id = id;
        this.name = "itemId";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
