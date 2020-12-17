package models.entities;

public class Order {
    private int id;
    private int count;
    private Product product;
    private Coupon coupon;

    public Order(int id, int count) {
        this.id = id;
        this.count = count;
    }
    public Order(int id, int count, Product product) {
        this(id, count);
        this.product = product;
    }
    public Order(int id, int count, Product product, Coupon coupon) {
        this(id, count, product);
        this.coupon = coupon;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
