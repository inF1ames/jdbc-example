package service;

import bl.Util;
import dao.UserDao;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends Util implements UserDao {

    private Connection connection;

    @Override

    public void add(User user) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO USERS (ID, LOGIN, FIRST_NAME, LAST_NAME, DELIVERY_ADDRESS, CONTACT_PHONE)" +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getDeliveryAddress());
            preparedStatement.setString(6, user.getContactPhone());

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
    public List<User> getAllUsers() throws SQLException {
        connection = getConnection();
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, LOGIN, FIRST_NAME, LAST_NAME, DELIVERY_ADDRESS, CONTACT_PHONE FROM USERS";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setLogin(resultSet.getString("LOGIN"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setDeliveryAddress(resultSet.getString("DELIVERY_ADDRESS"));
                user.setContactPhone(resultSet.getString("CONTACT_PHONE"));

                userList.add(user);
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

        return userList;
    }

    @Override
    public User getByLogin(String login) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "SELECT ID, LOGIN, FIRST_NAME, LAST_NAME, DELIVERY_ADDRESS, CONTACT_PHONE FROM USERS WHERE LOGIN=?";

        User user = new User();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            user.setId(resultSet.getLong("ID"));
            user.setLogin(resultSet.getString("LOGIN"));
            user.setFirstName(resultSet.getString("FIRST_NAME"));
            user.setLastName(resultSet.getString("LAST_NAME"));
            user.setDeliveryAddress(resultSet.getString("DELIVERY_ADDRESS"));
            user.setContactPhone(resultSet.getString("CONTACT_PHONE"));

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

        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE USERS SET LOGIN=?, FIRST_NAME=?, LAST_NAME=?, DELIVERY_ADDRESS=?, CONTACT_PHONE=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getDeliveryAddress());
            preparedStatement.setString(5, user.getContactPhone());
            preparedStatement.setLong(6, user.getId());

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
    public void remove(User user) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM USERS WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, user.getId());

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
