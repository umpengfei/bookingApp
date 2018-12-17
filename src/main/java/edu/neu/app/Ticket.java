package edu.neu.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Random;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "flightId")
    private int flightId;

    @Column(name = "airlineId")
    private int airlineId;

    @Column(name = "airline")
    private String airline;

    @Column(name = "date")
    private String date;

    @Column(name = "departure")
    private String departure;

    @Column(name = "destination")
    private String destination;


    public Ticket() {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
    }

    public Ticket(int input1, int input2) {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
        this.userId = input1;
        this.flightId = input2;
    }

//    public Ticket(String airline, int userId, String date, String departure, String destination, int flightId) {
//        Random random = new Random();
//        this.id = random.nextInt() & Integer.MAX_VALUE;
//        this.userId = userId;
//        this.airline = airline;
//        this.date = date;
//        this.departure = departure;
//        this.destination = destination;
//        this.flightId = flightId;
//    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getAirline() {
        return airline;
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

    public int getFlightId() {
        return flightId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setAirlineId(int id) {
        this.airlineId = id;
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

    public void setflightId(int flightId) {
        this.flightId = flightId;
    }

}
