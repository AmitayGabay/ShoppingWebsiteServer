package com.example.ShoppingWebsiteServer.service;

import com.example.ShoppingWebsiteServer.model.FavoriteRequest;
import com.example.ShoppingWebsiteServer.model.Item;
import com.example.ShoppingWebsiteServer.model.Order;
import com.example.ShoppingWebsiteServer.model.OrderRequest;

import java.util.List;

public interface OrderServiceInterface {
    Integer createNewOrder(Order order);

    Item addItemToOrder(FavoriteRequest favoriteRequest);

    String removeItemFromOrder(OrderRequest orderRequest);

    Order hasOpenOrder(Integer userId);

    Boolean isItemInTheOrder(OrderRequest orderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getUserOrders(Integer id);

    List<Item> getOrderItems(Integer id);

    Order closeOrder(Integer id);

    List<Order> getAllOrders();
}
