package com.example.ShoppingWebsiteServer.repository;

import com.example.ShoppingWebsiteServer.model.Item;
import com.example.ShoppingWebsiteServer.model.Order;
import com.example.ShoppingWebsiteServer.model.OrderRequest;

import java.util.List;

public interface OrderRepositoryInterface {
    Integer createNewOrder(Order order);

    Item addItemToOrder(Item item, Order order);

    String removeItemFromOrder(Item item, Order order);

    Order hasOpenOrder(Integer userId);

    Boolean isItemInTheOrder(OrderRequest orderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getUserOrders(Integer id);

    List<Item> getOrderItems(Integer id);

    Order closeOrder(Integer id);

    List<Order> getAllOrders();
}