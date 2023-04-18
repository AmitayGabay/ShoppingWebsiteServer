package com.example.ShoppingWebsiteServer.repository;

import com.example.ShoppingWebsiteServer.model.Order;
import com.example.ShoppingWebsiteServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements UserRepositoryInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String USERS_TABLE = "users";
    private static final String ORDERS_TABLE = "orders";
    private static final String ITEM_TO_USER_TABLE = "item_to_user";

    private static final String ITEM_TO_ORDER_TABLE = "item_to_order";

    @Override
    public User register(User user) {
        try {
            String sql = String.format("INSERT INTO %s (first_name, last_name, email, phone, address) VALUES (?,?,?,?,?)", USERS_TABLE);
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getAddress());
            User connectedUser = signIn(user.getEmail());
            return connectedUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User signIn(String email) {
        try {
            String sql = String.format("UPDATE %s SET is_connected = ? WHERE email = ?", USERS_TABLE);
            jdbcTemplate.update(sql, true, email);
            User connectedUser = getUserByEmail(email);
            return connectedUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User signOut(Integer id) {
        try {
            String sql = String.format("UPDATE %s SET is_connected = ? WHERE id = ?", USERS_TABLE);
            jdbcTemplate.update(sql, false, id);
            User updatedUser = getUserById(id);
            return updatedUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteUserById(Integer id) {
        try {
            String favoriteSql = String.format("DELETE FROM %s WHERE user_id = ?", ITEM_TO_USER_TABLE);
            jdbcTemplate.update(favoriteSql, id);
            String getOrderSql = String.format("SELECT * FROM %s WHERE user_id = ?", ORDERS_TABLE);
            List<Order> orders = jdbcTemplate.query(getOrderSql, new OrderMapper(), id);
            orders.forEach(order -> {
                String itemToOrderSql = String.format("DELETE FROM %s WHERE order_id = ?", ITEM_TO_ORDER_TABLE);
                jdbcTemplate.update(itemToOrderSql, order.getId());
            });
            String delOrderSql = String.format("DELETE FROM %s WHERE user_id = ?", ORDERS_TABLE);
            jdbcTemplate.update(delOrderSql, id);
            String sql = String.format("DELETE FROM %s WHERE id = ?", USERS_TABLE);
            jdbcTemplate.update(sql, id);
            return "User deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public User getUserById(Integer id) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ?", USERS_TABLE);
            User user = jdbcTemplate.queryForObject(sql, new UserMapper(), id);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE email = ?", USERS_TABLE);
            User user = jdbcTemplate.queryForObject(sql, new UserMapper(), email);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            String sql = String.format("SELECT * FROM %s", USERS_TABLE);
            List<User> users = jdbcTemplate.query(sql, new UserMapper());
            return users;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
