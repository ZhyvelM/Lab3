package models.entities.users;

import models.entities.Order;

import java.util.ArrayList;
import java.util.List;

abstract public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String number;
    private UserType userType;
    private List<Order> orders;
    private boolean banned;

    public User(int id, String name, String surname, String email, String password, String number, UserType userType, boolean banned) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.number = number;
        this.userType = userType;
        this.banned = banned;
        orders = new ArrayList<>();
    }
    public User(int id, String name, String surname, String email, String password, String number,
                UserType userType, List<Order> orders, boolean banned) {
        this(id, name, surname, email, password, number, userType, banned);
        this.orders = orders;
    }

    public void add(Order order) {
        orders.add(order);
    }
    public void remove(Order order) {
        orders.remove(order);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public boolean isBanned() {
        return banned;
    }
    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
