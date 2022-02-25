package entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FlightId implements Serializable {
    private String id;
    private long leg;

    public FlightId(){};

    public FlightId(String id, long leg){
        this.id = id;
        this.leg = leg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightId flightId = (FlightId) o;
        return leg == flightId.leg &&
                id.equals(flightId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leg);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlightId{");
        sb.append("id='").append(id).append('\'');
        sb.append(", leg=").append(leg);
        sb.append('}');
        return sb.toString();
    }
}
