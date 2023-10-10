package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
//    String URL = "jdbc:mysql://localhost:3306/mydbtest";
//    String  USERNAME = "root";
//    String PASSWORD = "Litany09091985/";
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
