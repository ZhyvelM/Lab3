package models.entities.users;

import models.entities.Order;

import java.util.List;

public class Client extends User {
    public Client(int id, String name, String surname, String email, String password, String number, boolean banned) {
        super(id, name, surname, email, password, number, UserType.Client, banned);
    }
    public Client(int id, String name, String surname, String email, String password, String number,
                  List<Order> orders, boolean banned) {
        super(id, name, surname, email, password, number, UserType.Client, orders, banned);
    }
}
