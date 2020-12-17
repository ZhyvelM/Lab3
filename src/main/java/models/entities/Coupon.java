package models.entities;

public class Coupon {
    private int id;
    private int discount;
    private String expireDate;

    public Coupon(int id, int discount, String expireDate) {
        this.id = id;
        this.discount = discount;
        this.expireDate = expireDate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public String getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
