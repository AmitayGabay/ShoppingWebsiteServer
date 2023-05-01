package com.example.ShoppingWebsiteServer.service;

import com.example.ShoppingWebsiteServer.model.*;
import com.example.ShoppingWebsiteServer.repository.ItemRepository;
import com.example.ShoppingWebsiteServer.repository.OrderRepository;
import com.example.ShoppingWebsiteServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer createNewOrder(Order order) {
        return orderRepository.createNewOrder(order);
    }

    @Override
    public Item addItemToOrder(FavoriteRequest favoriteRequest) {
        if (favoriteRequest.getItemId() == null) {
            System.out.println("You cannot add an item to order without item id");
            return null;
        }
        if (favoriteRequest.getUserId() == null) {
            System.out.println("You cannot add an item to order without user id");
            return null;
        }
        Item item = itemRepository.getItemById(favoriteRequest.getItemId());
        if (item == null) {
            System.out.println("The item with this id does not exist in the system");
            return null;
        }
        CustomUser user = userRepository.getUserById(favoriteRequest.getUserId());
        if (user == null) {
            System.out.println("The user with this id does not exist in the system");
            return null;
        }
        Order openOrder = hasOpenOrder(favoriteRequest.getUserId());
        if (openOrder == null) {
            Order newOrder = new Order(favoriteRequest.getUserId(), user.getAddress());
            Integer orderId = createNewOrder(newOrder);
            if (orderId == -1) {
                System.out.println("The creation of the order failed, so an item cannot be added to the order");
                return null;
            }
            openOrder = getOrderById(orderId);
        }
        return orderRepository.addItemToOrder(item, openOrder);
    }

    @Override
    public String removeItemFromOrder(OrderRequest orderRequest) {
        if (orderRequest.getItemId() == null) {
            return "You cannot remove an item from order without item id";
        }
        if (orderRequest.getOrderId() == null) {
            return "You cannot remove an item from order without order id";
        }
        Item item = itemRepository.getItemById(orderRequest.getItemId());
        if (item == null) {
            return "The item with this id does not exist in the system";
        }
        Order order = orderRepository.getOrderById(orderRequest.getOrderId());
        if (order == null) {
            return "The order with this id does not exist in the system";
        }
        if (!isItemInTheOrder(orderRequest)) {
            return "The item is not in order and therefore cannot be removed from there";
        }
        if (order.getStatus() == OrderStatus.CLOSE) {
            return "It is not possible to remove an item from a closed order";
        }
        return orderRepository.removeItemFromOrder(item, order);
    }

    @Override
    public Order updateAddressInOrder(UpdateAddressRequest updateAddressRequest) {
        if (updateAddressRequest.getOrderId() == null) {
            System.out.println("You cannot update address in order without order id");
            return null;
        }
        if (updateAddressRequest.getShippingAddress() == null || updateAddressRequest.getShippingAddress().trim().isEmpty()) {
            System.out.println("You cannot update address in order without address");
            return null;
        }
        Order order = orderRepository.getOrderById(updateAddressRequest.getOrderId());
        if (order == null) {
            System.out.println("The order with this id does not exist in the system");
            return null;
        }
        if (order.getStatus() == OrderStatus.CLOSE) {
            System.out.println("It is not possible to change the shipping address of a closed order");
            return null;
        }
        return orderRepository.updateAddressInOrder(updateAddressRequest);
    }

    @Override
    public Order hasOpenOrder(Integer userId) {
        if (userId == null) {
            System.out.println("You cannot get order without user id");
            return null;
        }
        CustomUser user = userRepository.getUserById(userId);
        if (user == null) {
            System.out.println("The user with this id does not exist in the system");
            return null;
        }
        return orderRepository.hasOpenOrder(userId);
    }

    @Override
    public Boolean isItemInTheOrder(OrderRequest orderRequest) {
        return orderRepository.isItemInTheOrder(orderRequest);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        if (orderId == null) {
            System.out.println("The order cannot be accepted without order id");
            return null;
        }
        return orderRepository.getOrderById(orderId);
    }

    @Override
    public List<Order> getUserOrders(Integer id) {
        if (id == null) {
            System.out.println("You cannot get user orders without user id");
            return null;
        }
        CustomUser user = userRepository.getUserById(id);
        if (user == null) {
            System.out.println("The user with this id does not exist in the system");
            return null;
        }
        return orderRepository.getUserOrders(id);
    }

    @Override
    public List<Item> getOrderItems(Integer id) {
        if (id == null) {
            System.out.println("You cannot get order items without order id");
            return null;
        }
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            System.out.println("The order with this id does not exist in the system");
            return null;
        }
        return orderRepository.getOrderItems(id);
    }

    @Override
    public Order closeOrder(Integer id) {
        if (id == null) {
            System.out.println("You cannot close order without order id");
            return null;
        }
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            System.out.println("The order with this id does not exist in the system");
            return null;
        }
        return orderRepository.closeOrder(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }
}
