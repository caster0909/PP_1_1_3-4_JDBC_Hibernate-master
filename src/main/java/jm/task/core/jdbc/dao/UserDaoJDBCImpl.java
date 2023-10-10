package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        //IF NOT EXIST если еще не существует
        final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users" +
                "(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "user_name VARCHAR (30)," +
                "user_lastname VARCHAR (30)," +
                "user_age INT(3));";
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(CREATE_TABLE_USERS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Удалить таблицу
    public void dropUsersTable() {
        final String DROP_TABLE_USERS = "DROP TABLE IF EXISTS users";
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(DROP_TABLE_USERS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        final String SAVE_USERS = "INSERT INTO users(user_name, user_lastname, user_age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SAVE_USERS)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        final String REMOVE_USERS_BY_ID = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(REMOVE_USERS_BY_ID)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {
        final String GET_ALL_USERS = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()){
//Метод executeQuery выполняет переданный SQL-запрос и возвращает ResultSet, который представляет собой набор данных,
// полученных в результате выполнения запроса. ResultSet содержит методы для доступа к данным в результирующем наборе,
// такие как next(), getLong(), getString(), getByte() и другие.
//Результат (ResultSet)
//Экземпляры этого элемента содержат данные, которые были получены в результате выполнения SQL – запроса.
// Он работает как итератор и “пробегает” по полученным данным.
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("user_name"));
                user.setLastName(resultSet.getString("user_lastname"));
                user.setAge(resultSet.getByte("user_age"));
                userList.add(user);
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        return userList;
    }

    public void cleanUsersTable() {
        final String CLEAN_USERS_TABLE = "TRUNCATE TABLE users";
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(CLEAN_USERS_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}