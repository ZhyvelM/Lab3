package models.dao;

import models.dao.interfaces.UserDao;
import models.entities.users.Client;
import models.entities.users.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

class UserDaoImplTest {
    UserDao userDao;
    User user;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        userDao = new UserDaoImpl();
        user = new Client(
                0,
                "Petya",
                "Yakimovich",
                "example@gmail.com",
                "12345678",
                "+375331234567",
                false);
        userDao.insert(user);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        userDao.delete(user);
    }

    @org.junit.jupiter.api.Test
    void findById() {
        User new_user = userDao.findById(user.getId());
        assertReflectionEquals(new_user, user);
    }

    @org.junit.jupiter.api.Test
    void findByType() {
        List<User> new_users = userDao.findByType(user.getUserType());
        List<User> users = new ArrayList<>();
        users.add(user);
        assertReflectionEquals(new_users, users);
    }

    @org.junit.jupiter.api.Test
    void findAll() {
        List<User> new_users = userDao.findAll();
        List<User> users = new ArrayList<>();
        users.add(user);
        assertReflectionEquals(new_users, users);
    }

    @org.junit.jupiter.api.Test
    void insert() {
        User new_user = userDao.findById(user.getId());
        assertReflectionEquals(new_user, user);
    }

    @org.junit.jupiter.api.Test
    void update() {
        user.setName("New Name");
        userDao.update(user);
        User new_user = userDao.findById(user.getId());
        assertReflectionEquals(new_user, user);
    }

    @org.junit.jupiter.api.Test
    void delete() {
        userDao.delete(user);
        User new_user = userDao.findById(user.getId());
        org.junit.jupiter.api.Assertions.assertNull(new_user);
    }
}