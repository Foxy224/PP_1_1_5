package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Bob", "Kolin", (byte) 34);
        service.saveUser("Vasya", "Pupkin", (byte) 104);
        service.saveUser("Liam", "Nasty", (byte) 24);
        service.saveUser("Boss", "Kujim", (byte) 49);

        List<User> users = service.getAllUsers();

        for (User user: users) {
            System.out.println(user.toString());
        }

        service.cleanUsersTable();

        service.dropUsersTable();
    }
}
