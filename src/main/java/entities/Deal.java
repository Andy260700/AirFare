package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Deals")
public class Deal implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dealId;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "deal_starts", nullable = false)
    private Date startingTime;

    @Column(name = "deal_ends", nullable = false)
    private Date endingTime;

    @OneToOne(mappedBy = "deal")
    private Flight flight;

    public Deal() {}

    public Deal(double price, Date startingTime, Date endingTime, Flight flight) {
        this.price = price;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.flight = flight;
    }

    public long getUserId() {
        return dealId;
    }

    public void setUserId(Object id) {
        System.out.println(id);
        this.dealId = (Long)id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Deal{");
        sb.append("price=").append(price);
        sb.append(", startingTime=").append(startingTime);
        sb.append(", endingTime=").append(endingTime);
        sb.append(", flight=").append(flight);
        sb.append('}');
        return sb.toString();
    }
}
