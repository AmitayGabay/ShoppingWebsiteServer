package com.example.ShoppingWebsiteServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateAddressRequest {
    @JsonProperty(value = "order_id")
    private Integer orderId;
    @JsonProperty(value = "shipping_address")
    private String shippingAddress;

    public UpdateAddressRequest(Integer orderId, String shippingAddress) {
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
