package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Deals")
public class Deal implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name = "price", nullable = false)
    private double price;

    public Deal() {}

    public Deal(double price) {
        this.price = price;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Object id) {
        System.out.println(id);
        this.userId = (Long)id;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "userId=" + userId +
                ", price=" + price +
                '}';
    }
}
