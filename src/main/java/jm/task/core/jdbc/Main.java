package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import net.bytebuddy.implementation.ToStringMethod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceHibernateImpl userServiceHibernate = new UserServiceHibernateImpl();
        //userServiceHibernate.createUsersTable();

//        List<User> userList = userServiceHibernate.getAllUsers();
//        for (User users : userList) {
//            System.out.println(users);
//        }

        //UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
       userServiceHibernate.saveUser("Petr", "Yan", (byte) 20);
       userServiceHibernate.saveUser("Olga", "Stelmashok", (byte) 45);


            //UserServiceImpl userService = new UserServiceImpl();
////


        //userService.saveUser("Dima", "Shaman", (byte) 41);
//        userService.saveUser("Vasya", "Trisik", (byte) 31);
//        userService.saveUser("Vasya", "Pushkin", (byte) 30);
        //userService.removeUserById(7);
        //System.out.println((userService.getAllUsers()));
        //userService.removeUserById(9);
        //userService.cleanUsersTable();
        //userService.createUsersTable();
        //UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.createUsersTable();
//        System.out.println(userService.getAllUsers());



    }

}
