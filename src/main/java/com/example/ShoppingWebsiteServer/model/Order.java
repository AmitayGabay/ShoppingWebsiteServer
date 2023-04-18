package com.example.ShoppingWebsiteServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Order {
    private Integer id;
    @JsonProperty(value = "user_id")
    private Integer userId;
    @JsonProperty(value = "order_date")
    private LocalDate orderDate;
    @JsonProperty(value = "shipping_address")
    private String shippingAddress;
    @JsonProperty(value = "total_price")
    private Double totalPrice;
    private OrderStatus status;

    public Order(Integer userId){
        this.userId = userId;
        this.orderDate = LocalDate.now();
    }
    public Order(Integer id, Integer userId, LocalDate orderDate, String shippingAddress, Double totalPrice, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        if(orderDate == null) {
            this.orderDate = LocalDate.now();
        }
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
