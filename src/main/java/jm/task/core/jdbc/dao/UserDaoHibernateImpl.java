package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        String sql = """
                        CREATE TABLE IF NOT EXISTS users (
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(45) NULL,
                        `lastName` VARCHAR(45) NULL,
                        `age` INT(3) NULL,
                        PRIMARY KEY (`id`))
                    """;

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {

        String sql = "drop table if exists `users`.`users`";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = getSessionFactory().openSession()) {
            User user = new User(name, lastName, age);
            session.save(user);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println("User с именем " + name + " был добавлен в таблицу");
    }

    @Override
    public void removeUserById(long id) {

        String sql = "delete from users where id = :id";

        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {

        String sql = "select * from users";

        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            List<User> usersList = query.list();
            return usersList;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void cleanUsersTable() {

        String sql = "DELETE FROM users.users";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}
