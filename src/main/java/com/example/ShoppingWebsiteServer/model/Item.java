package com.example.ShoppingWebsiteServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private Integer id;
    private String title;
    private String picture;
    @JsonProperty(value = "usd_price")
    private Double usdPrice;
    private Integer amount;

    public Item(Integer id, String title, String picture, Double usdPrice, Integer amount) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.usdPrice = usdPrice;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(Double usdPrice) {
        this.usdPrice = usdPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
