package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.beans.BeanProperty;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String dbURL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=Europe/Minsk&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Litany09091985/";


    //Connection для Hibernate мой способ
    private static SessionFactory sessionFactory;
    public static SessionFactory getHibernateConnection(){
        if (sessionFactory == null){
            try{
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver"); //Правильный ли драйвер? Правильный ли путь к нему?
                configuration.setProperty("hibernate.connection.url", dbURL);
                configuration.setProperty("hibernate.connection.username", USERNAME);
                configuration.setProperty("hibernate.connection.password", PASSWORD);
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty("hibernate.show_sql", "true");

                //Добавление классов сущностей
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }




    //Connection для JDBC
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Class.forName отвечает за загрузку драйвера JDBC
            // для работы с базой данных MySQL.
            connection = DriverManager.getConnection(dbURL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}