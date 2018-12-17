package edu.neu.app;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class UserDAO {

    private Map<Integer, User> users;
    private Map<Integer, Ticket> tickets;
    private Map<Integer, List<Ticket>> userTickets;
    private Map<Integer, List<Ticket>> airlineTickets;
    private Map<Integer, Airline> airlines;
    private Map<Integer, Flight> flights;
    private Map<Integer, Integer> flightEnrolled;
    private static UserDAO unique;

    private UserDAO() {
        this.users = new ConcurrentHashMap<>();
        this.flights = new ConcurrentHashMap<>();
        this.tickets = new ConcurrentHashMap<>();
        this.userTickets = new ConcurrentHashMap<>();
        this.airlineTickets = new ConcurrentHashMap<>();
        this.airlines = new ConcurrentHashMap<>();
        this.flightEnrolled = new ConcurrentHashMap<>();
    }

    static UserDAO getUserDAO() {
        if (unique == null) {
            unique = new UserDAO();
        }
        return unique;
    }

    // get all users
    List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    // get user by id
    User getUser(int id) {
        return users.get(id);
    }

    // create user
    User createUser(User input) {
        User u = new User(input.getName(), input.getAge(), input.getMoney());
        users.put(u.getId(), u);
        userTickets.put(u.getId(), new ArrayList<>());
        return u;
    }

    // get people enrolled in a flight
    int getEnrolled(int id) {
        return flightEnrolled.getOrDefault(id, 0);
    }

    void addEnrolled(int id) {
        flightEnrolled.put(id, flightEnrolled.getOrDefault(id, 0) + 1);
    }

    void deleteEnrolled(int id) {
        if (!flightEnrolled.containsKey(id) || flightEnrolled.get(id) == 0) {
            throw new RuntimeException();
        }
        flightEnrolled.put(id, flightEnrolled.get(id) - 1);
    }

    // create a new flight
    Flight createFlight(Flight flight) {
        Flight f = new Flight(flight.getAirlineId(), flight.getDate(), flight.getDeparture(), flight.getDestination(), flight.getCapacity(), flight.getPrice());
        flights.put(f.getId(), f);
        flightEnrolled.put(f.getId(), 0);
        return f;
    }

    // get all flights
    List<Flight> getFlights() {
        return new ArrayList<>(flights.values());
    }

    // get flight by id
    Flight getFlight(int id) {
        return flights.get(id);
    }

    // get the tickets bought by a user
    List<Ticket> getTickets(int id) {
        return userTickets.get(id);
    }

    // get the tickets sought by an airline
    List<Ticket> getAirlineTickets(int id) {
        return airlineTickets.get(id);
    }

    Airline createAirLine(Airline input) {
        Airline airline = new Airline(input.getName());
        airlines.put(airline.getId(), airline);
        airlineTickets.put(airline.getId(), new ArrayList<>());
        return airline;
    }

    // buy a ticket
    Ticket createTicket(Ticket ticket) {
        Ticket t = new Ticket(ticket.getUserId(), ticket.getFlightId());
        t.setAirline(ticket.getAirline());
        t.setDeparture(ticket.getDeparture());
        t.setDestination(ticket.getDestination());
        t.setDate(ticket.getDate());
        t.setAirlineId(ticket.getAirlineId());
        List<Ticket> userTicketList = userTickets.get(t.getUserId());
        userTicketList.add(t);
        List<Ticket> airlineTicketList = airlineTickets.get(t.getAirlineId());
        airlineTicketList.add(t);
        tickets.put(t.getId(), t);
        return t;
    }

    // get a ticket by id
    Ticket getTicket(int id) {
        if (!tickets.containsKey(id)) {
            throw new RuntimeException();
        }
        return tickets.get(id);
    }

    // get airline by id
    Airline getAirline(int id) {
        return airlines.get(id);
    }

    // get all airlines
    List<Airline> getAirlines() {
        return new ArrayList<>(airlines.values());
    }
}
