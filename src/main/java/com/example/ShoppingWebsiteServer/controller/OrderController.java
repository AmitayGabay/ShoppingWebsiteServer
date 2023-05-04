package com.example.ShoppingWebsiteServer.controller;

import com.example.ShoppingWebsiteServer.model.*;
import com.example.ShoppingWebsiteServer.service.OrderService;
import com.example.ShoppingWebsiteServer.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "add-item-to-order", params = "Authorization")
    public Item addItemToOrder( @RequestParam(value = "Authorization") String token, @RequestBody Integer itemId) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return orderService.addItemToOrder(username, itemId);
    }

    @DeleteMapping(value = "remove-item-from-order", params = "Authorization")
    public String removeItemFromOrder(@RequestParam(value = "Authorization") String token, @RequestBody OrderRequest orderRequest) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return orderService.removeItemFromOrder(username, orderRequest);
    }

    @PutMapping(value = "apdate-address-in-order", params = "Authorization")
    public Order updateAddressInOrder(@RequestParam(value = "Authorization") String token, @RequestBody UpdateAddressRequest updateAddressRequest) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return orderService.updateAddressInOrder(username, updateAddressRequest);
    }

    @GetMapping(params = {"Authorization", "orderid"})
    public Order getOrderById(@RequestParam(value = "Authorization") String token, @RequestParam(value = "orderid") Integer orderId) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return orderService.getOrderById(username, orderId);
    }

    @GetMapping(value = "/user-orders", params = "Authorization")
    public List<Order> getUserOrders(@RequestParam(value = "Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return orderService.getUserOrders(username);
    }

    @GetMapping(value = "/order-items", params = {"Authorization", "id"})
    public List<Item> getOrderItems(@RequestParam(value = "Authorization") String token, @RequestParam Integer id) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return orderService.getOrderItems(username, id);
    }

    @PutMapping(value = "/close", params = {"Authorization", "id"})
    public Order closeOrder(@RequestParam(value = "Authorization") String token, @RequestParam Integer id) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return orderService.closeOrder(username, id);
    }

    @GetMapping(value = "/all-orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
