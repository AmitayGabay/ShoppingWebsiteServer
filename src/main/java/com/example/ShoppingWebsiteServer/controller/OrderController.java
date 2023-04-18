package com.example.ShoppingWebsiteServer.controller;

import com.example.ShoppingWebsiteServer.model.*;
import com.example.ShoppingWebsiteServer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "add-item-to-order")
    public Item addItemToOrder(@RequestBody FavoriteRequest favoriteRequest) {
        return orderService.addItemToOrder(favoriteRequest);
    }

    @DeleteMapping(value = "remove-item-from-order")
    public String removeItemFromOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.removeItemFromOrder(orderRequest);
    }

    @PutMapping(value = "apdate-address-in-order")
    public Order updateAddressInOrder(@RequestBody UpdateAddressRequest updateAddressRequest) {
        return orderService.updateAddressInOrder(updateAddressRequest);
    }

    @GetMapping(params = "userid")
    public Order hasOpenOrder(@RequestParam(value = "userid") Integer userId) {
        return orderService.hasOpenOrder(userId);
    }

    @PostMapping(value = "is-item-in-the-order")
    public Boolean isItemInTheOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.isItemInTheOrder(orderRequest);
    }

    @GetMapping(params = "orderid")
    public Order getOrderById(@RequestParam(value = "orderid") Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping(value = "/user-orders", params = "id")
    public List<Order> getUserOrders(@RequestParam Integer id) {
        return orderService.getUserOrders(id);
    }

    @GetMapping(value = "/order-items", params = "id")
    public List<Item> getOrderItems(@RequestParam Integer id) {
        return orderService.getOrderItems(id);
    }

    @PutMapping(value = "/close", params = "id")
    public Order closeOrder(@RequestParam Integer id) {
        return orderService.closeOrder(id);
    }

    @GetMapping(value = "/all-orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
