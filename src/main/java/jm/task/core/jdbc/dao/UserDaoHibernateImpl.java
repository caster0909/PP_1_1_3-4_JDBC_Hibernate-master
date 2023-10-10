package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
  List<User> users;

    @Override
    public void createUsersTable() {
        try (Session session = Util.getHibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction(); //начинаем транзакцию

            String sql = "CREATE TABLE IF NOT EXIST users" +
                         "(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                         "name VARCHAR (30)," +
                         "lastname VARCHAR (30)," +
                    "age INT(3));";

            Query query = session.createNativeQuery(sql);
            query.executeUpdate(); //выполняем запрос для создания таблицы
            transaction.commit(); //фиксируем транзакцию
            System.out.println("Таблица users успешно создана");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        try (Session session = Util.getHibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getHibernateConnection().openSession() ) {
            Transaction transaction = session.beginTransaction();
            User user = new User (name, lastName, age);
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getHibernateConnection().openSession() ) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                transaction.commit();
                System.out.println("Пользователь" + user.getName() + " успешно удален из таблицы!");
            }
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getHibernateConnection().openSession();) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);

            Query<User> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getHibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
            criteriaDelete.from(User.class);

            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
