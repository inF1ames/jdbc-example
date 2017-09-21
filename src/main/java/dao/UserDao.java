package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void add(User user) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    User getByLogin(String login) throws SQLException;
    void update(User user) throws SQLException;
    void remove(User user) throws SQLException;
}
