package models.entities.users;

import models.entities.Order;

import java.util.List;

public class Admin extends User {
    public Admin(int id, String name, String surname, String email, String password, String number, boolean banned) {
        super(id, name, surname, email, password, number, UserType.Admin, banned);
    }
    public Admin(int id, String name, String surname, String email, String password, String number,
                 List<Order> orders, boolean banned) {
        super(id, name, surname, email, password, number, UserType.Admin, orders, banned);
    }
}