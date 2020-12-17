package models.entities;


import models.entities.users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> parts;
    private List<User> users;

    public Store() {
        parts = new ArrayList<Product>();
        users = new ArrayList<User>();
    }

    public void add(Product product) {
        parts.add(product);
    }
    public void add(User user) {
        users.add(user);
    }
    public void remove(Product product) {
        parts.remove(product);
    }
    public void remove(User user) {
        users.remove(user);
    }

    public static Store deserialize() {
        Store store = null;
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/store.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            store = (Store) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return store;
    }
    public void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream("/tmp/store.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}