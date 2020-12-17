package models.entities;

public class Product {
    private int id;
    private String manufacturer;
    private String name;
    private double cost;
    private int count;

    public Product(int id, String manufacturer, String name, double cost, int count) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.cost = cost;
        this.count = count;
    }

    private boolean isAvailable()
    {
        return count != 0;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
