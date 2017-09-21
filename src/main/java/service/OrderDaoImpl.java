package service;

import bl.Util;
import dao.OrderDao;
import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends Util implements OrderDao {

    private Connection connection;

    @Override
    public void add(Order order) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO STORE_ORDER (ID, USER_ID, ITEM_ID, ORDER_DATE, QUANTITY) VALUES(?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, order.getId());
            preparedStatement.setLong(2, order.getUserId());
            preparedStatement.setLong(3, order.getItemId());
            preparedStatement.setDate(4, (Date) order.getDate());
            preparedStatement.setInt(5, order.getQuantity());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();

            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        connection = getConnection();
        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT ID, USER_ID, ITEM_ID, ORDER_DATE, QUANTITY FROM STORE_ORDER";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("ID"));
                order.setUserId(resultSet.getLong("USER_ID"));
                order.setItemId(resultSet.getLong("ITEM_ID"));
                order.setDate(resultSet.getDate("ORDER_DATE"));
                order.setQuantity(resultSet.getInt("QUANTITY"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return orderList;
    }

    @Override
    public Order getByUserId(Long userId) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "SELECT ID, USER_ID, ITEM_ID, ORDER_DATE, QUANTITY FROM STORE_ORDER WHERE USER_ID=?";

        Order order = new Order();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            order.setId(resultSet.getLong("ID"));
            order.setUserId(resultSet.getLong("USER_ID"));
            order.setItemId(resultSet.getLong("ITEM_ID"));
            order.setDate(resultSet.getDate("ORDER_DATE"));
            order.setQuantity(resultSet.getInt("QUANTITY"));

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return order;
    }

    @Override
    public void update(Order order) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE STORE_ORDER SET USER_ID=?, ITEM_ID=?, ORDER_DATE=?, QUANTITY=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getItemId());
            preparedStatement.setDate(3, order.getDate());
            preparedStatement.setInt(4, order.getQuantity());
            preparedStatement.setLong(5, order.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Order order) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM STORE_ORDER WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, order.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
