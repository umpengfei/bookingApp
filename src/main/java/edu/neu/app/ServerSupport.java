package edu.neu.app;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

public class ServerSupport {

    private UserDAO userDAO = UserDAO.getUserDAO();
    private DAO dao = DAO.getDAO();

    public ServerSupport() {
    }

    // get all users;
//    public List<User> getUsers() {
//        return userDAO.getUsers();
//    }
    public List<User> getUsers() {
        return dao.getUsers();
    }

    // get user by id;
//    public User getUser(int id) {
//        return userDAO.getUser(id);
//    }
    public User getUser(int id) {
        return dao.getUser(id);
    }

    // create a new user
//    public User createUser(User user) {
//        return userDAO.createUser(user);
//    }
    public User createUser(User user) {
        return dao.createUser(user);
    }

    // delete a user
    public List<User> deleteUser(int id) {
        return dao.deleteUser(id);
    }

    // get all flights;
//    public List<Flight> getFlights() {
//        return userDAO.getFlights();
//    }
    public List<Flight> getFlights() {
        return dao.getFlights();
    }

    // get flight by id;
//    public Flight getFlight(int id) {
//        return userDAO.getFlight(id);
//    }
    public Flight getFlight(int id) {
        return dao.getFlight(id);
    }

    // search for a flight
//    public List<Flight> searchFlights(String date, String dep, String dest) {
//        List<Flight> ans = new ArrayList<>();
//        List<Flight> list = userDAO.getFlights();
//        for (Flight flight : list) {
//            int curCapacity = userDAO.getEnrolled(flight.getId());
//            // only when a flight is not full && it operates on the same day as searched && it has the same departure place and destination as searched
//            if (flight.getCapacity() > curCapacity
//                    && flight.getDate().substring(0, 10).equals(date)
//                    && flight.getDeparture().equals(dep)
//                    && flight.getDestination().equals(dest)) {
//                ans.add(flight);
//            }
//        }
//        return ans;
//    }
        public List<Flight> searchFlights(String date, String dep, String dest) {
        List<Flight> ans = new ArrayList<>();
        List<Flight> list = dao.getFlights();
        for (Flight flight : list) {
            int curCapacity = flight.getEnrolled();
            // only when a flight is not full && it operates on the same day as searched && it has the same departure place and destination as searched
            if (flight.getCapacity() > curCapacity
                    && flight.getDate().substring(0, 10).equals(date)
                    && flight.getDeparture().equals(dep)
                    && flight.getDestination().equals(dest)) {
                ans.add(flight);
            }
        }
        return ans;
    }

    // create a new flight
//    public Flight createFlight(Flight flight) {
//        return userDAO.createFlight(flight);
//    }
    public Flight createFlight(Flight flight) {
        return dao.createFlight(flight);
    }

    // get a user's tickets
//    public List<Ticket> getTickets(int id) {
//        return userDAO.getTickets(id);
//    }
    public List<Ticket> getTickets(int id) {
        return dao.getTickets(id);

    }

    // get tickets an airline sought
//    public List<Ticket> getAirlineTickets(int id) {
//        return userDAO.getAirlineTickets(id);
//    }
    public List<Ticket> getAirlineTickets(int id) {
        return dao.getAirlineTickets(id);
    }

    // buy a ticket
//    public Ticket createTicket(Ticket ticket) {
//        int userId = ticket.getUserId();
//        int flightId = ticket.getFlightId();
//        User user = userDAO.getUser(userId);
//        Flight flight = userDAO.getFlight(flightId);
//        Airline airline = userDAO.getAirline(flight.getAirlineId());
//        if (user.getMoney() < flight.getPrice() || userDAO.getEnrolled(flightId) >= flight.getCapacity()) {
//            throw new RuntimeException();
//        } else {
//            // user will pay for the ticket
//            user.setMoney(user.getMoney() - flight.getPrice());
//            // airline will get profit from the ticket
//            airline.setProfit(airline.getProfit() + flight.getPrice());
//            // flight current enrolled number increment 1
//            userDAO.addEnrolled(flightId);
//        }
//        //put ticket into database
//        ticket.setAirline(airline.getName());
//        ticket.setDeparture(flight.getDeparture());
//        ticket.setDestination(flight.getDestination());
//        ticket.setDate(flight.getDate());
//        ticket.setAirlineId(airline.getId());
//        return userDAO.createTicket(ticket);
//    }
    // buy a ticket
        public Ticket createTicket(Ticket ticket) {
        int userId = ticket.getUserId();
        int flightId = ticket.getFlightId();
        User user = dao.getUser(userId);
        Flight flight = dao.getFlight(flightId);
        Airline airline = dao.getAirline(flight.getAirlineId());
        if (user.getMoney() < flight.getPrice() || flight.getEnrolled() >= flight.getCapacity()) {
            throw new RuntimeException();
        } else {
            // user will pay for the ticket
            dao.updateUserMoney(user, flight.getPrice());
            // airline will get profit from the ticket
            dao.updateAirlineProfit(airline, flight.getPrice());
            // flight current enrolled number increment 1
            dao.upDateFlightEnrolled(flight, 1);
        }
        //put ticket into database
        ticket.setAirline(airline.getName());
        ticket.setDeparture(flight.getDeparture());
        ticket.setDestination(flight.getDestination());
        ticket.setDate(flight.getDate());
        ticket.setAirlineId(airline.getId());
        return dao.createTicket(ticket);
    }

    // get a refund of ticket
//    public List<Ticket> refundTicket(int id) {
//        Ticket t = userDAO.getTicket(id);
//        User user = userDAO.getUser(t.getUserId());
//        Flight flight = userDAO.getFlight(t.getFlightId());
//        Airline airline = userDAO.getAirline(flight.getAirlineId());
//        // remove this ticket from user's ticket list
//        List<Ticket> userList = userDAO.getTickets(user.getId());
//        userList.remove(t);
//        // remove this ticket from airline's ticket list
//        List<Ticket> airlineList = userDAO.getAirlineTickets(airline.getId());
//        airlineList.remove(t);
//        // put money back to user's money
//        user.setMoney(user.getMoney() + flight.getPrice());
//        // remove money from airline's account
//        airline.setProfit(airline.getProfit() - flight.getPrice());
//        // flight current enrolled number decreased by 1
//        userDAO.deleteEnrolled(flight.getId());
//        // return user's current ticket list
//        return userList;
//    }
        public List<Ticket> refundTicket(int id) {
        Ticket t = dao.getTicket(id);
        User user = dao.getUser(t.getUserId());
        Flight flight = dao.getFlight(t.getFlightId());
        Airline airline = dao.getAirline(flight.getAirlineId());
        // remove this ticket from user's ticket list
        List<Ticket> userList = dao.getTickets(user.getId());
        dao.userTicketRemove(user, id);
        // remove this ticket from airline's ticket list
        dao.airlineTicketRemove(airline, id);
        // put money back to user's money
        dao.updateUserMoney(user, -flight.getPrice());
        // remove money from airline's account
        dao.updateAirlineProfit(airline, -flight.getPrice());
        // flight current enrolled number decreased by 1
        dao.upDateFlightEnrolled(flight, -1);
        // return user's current ticket list
        return dao.getTickets(user.getId());
    }

    // get all airlines
//    public List<Airline> getAirlines() {
//        return userDAO.getAirlines();
//    }
        public List<Airline> getAirlines() {
        return dao.getAirlines();
    }

    // create an Airline
//    public Airline createAirline(Airline airline) {
//        return userDAO.createAirLine(airline);
//    }
        public Airline createAirline(Airline airline) {
        return dao.createAirLine(airline);
    }

    // get airline by id
//    public Airline getAirline(int id) {
//        return userDAO.getAirline(id);
//    }
    public Airline getAirline(int id) {
        return dao.getAirline(id);
    }

}
