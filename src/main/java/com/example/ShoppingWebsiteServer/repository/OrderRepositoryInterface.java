package com.example.ShoppingWebsiteServer.repository;

import com.example.ShoppingWebsiteServer.model.Item;
import com.example.ShoppingWebsiteServer.model.Order;
import com.example.ShoppingWebsiteServer.model.OrderRequest;
import com.example.ShoppingWebsiteServer.model.UpdateAddressRequest;

import java.util.List;

public interface OrderRepositoryInterface {
    Integer createNewOrder(Order order);

    Item addItemToOrder(Item item, Order order);

    String removeItemFromOrder(Item item, Order order);

    Order updateAddressInOrder(UpdateAddressRequest updateAddressRequest, Integer userId);

    Order hasOpenOrder(Integer userId);

    Boolean isItemInTheOrder(OrderRequest orderRequest);

    Order getOrderById(Integer orderId, Integer userId);

    List<Order> getUserOrders(Integer id);

    List<Item> getOrderItems(Integer id);

    Order closeOrder(Integer id, Integer userId);

    List<Order> getAllOrders();
}
