package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Flights")
public class Flight implements Serializable {

//    @EmbeddedId
//    @Column(name="flight_id")
//    private FlightId flightId;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "leg", nullable = false)
    private Long leg;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "departure_time", nullable = false)
    private Date departureTime;

    @Column(name = "arrivalTime", nullable = false)
    private Date arrivalTime;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="flight_deals",
            joinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "deal_id", referencedColumnName = "id")}
    )
    private Deal deal;

    public Flight(){};

    public Flight(String flightNumber,Long leg, String owner, String source, String destination, Date departureTime, Date arrivalTime, double price) {
        this.flightNumber = flightNumber;
        this.leg = leg;
        this.owner = owner;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public Long getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOwner() {
        return owner;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public Long getLeg() {
        return leg;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLeg(Long leg) {
        this.leg = leg;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("flightNumber='").append(flightNumber).append('\'');
        sb.append(", leg='").append(leg).append('\'');
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", destination='").append(destination).append('\'');
        sb.append(", departureTime=").append(departureTime);
        sb.append(", arrivalTime=").append(arrivalTime);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

}
