package dao;

import entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    void add(Order order) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
    Order getByUserId(Long userId) throws SQLException;
    void update(Order order) throws SQLException;
    void remove(Order order) throws SQLException;
}
