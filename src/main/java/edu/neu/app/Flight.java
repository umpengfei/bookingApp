package edu.neu.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Random;

@Entity
@Table(name = "Flight")
public class Flight {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "airlineId")
    private int airlineId;

    @Column(name = "date")
    private String date;

    @Column(name = "departure")
    private String departure;

    @Column(name = "destination")
    private String destination;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "price")
    private int price;

    @Column(name = "enrolled")
    private int enrolled;

    public Flight() {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
    }

    public Flight(int airlineId, String date, String departure, String destination, int capacity, int price) {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
        this.airlineId = airlineId;
        this.date = date;
        this.departure = departure;
        this.destination = destination;
        this.capacity = capacity;
        this.price = price;
        this.enrolled = 0;
    }


    public int getId() {
        return id;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public String getDate() {
        return date;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }


}
