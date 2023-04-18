package com.example.ShoppingWebsiteServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteRequest {
    @JsonProperty(value = "item_id")
    private Integer itemId;
    @JsonProperty(value = "user_id")
    private Integer userId;

    public FavoriteRequest(Integer itemId, Integer userId) {
        this.itemId = itemId;
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getUserId() {
        return userId;
    }
}
