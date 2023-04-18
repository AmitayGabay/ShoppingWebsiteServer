package com.example.ShoppingWebsiteServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {
    @JsonProperty(value = "item_id")
    private Integer itemId;
    @JsonProperty(value = "order_id")
    private Integer orderId;

    public OrderRequest(Integer itemId, Integer orderId) {
        this.itemId = itemId;
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
