package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        openTransactionSession();

        String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` INT(3) NULL,\n" +
                "  PRIMARY KEY (`id`))";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void dropUsersTable() {
        openTransactionSession();

        String sql = "drop table if exists `users`.`users`";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        openTransactionSession();

        Session session = getSession();
        User user = new User(name, lastName, age);
        session.save(user);

        closeTransactionSession();

        System.out.println("User с именем " + name + " был добавлен в таблицу");
    }

    @Override
    public void removeUserById(long id) {
        openTransactionSession();

        String sql = "delete from users where id = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("id", id);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public List<User> getAllUsers() {
        openTransactionSession();

        String sql = "select * from users";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        List<User> usersList = query.list();

        closeTransactionSession();

        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        openTransactionSession();

        String sql = "DELETE FROM users.users";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.executeUpdate();

        closeTransactionSession();
    }
}
