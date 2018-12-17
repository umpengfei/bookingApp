package edu.neu.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DAO {
    private static DAO unique;

    // initialize database
    static DAO getDAO() {
        if (unique == null) {
            unique = new DAO();
        }
        return unique;
    }

    // create SessionFactory
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(edu.neu.app.User.class);
        configuration.addAnnotatedClass(edu.neu.app.Airline.class);
        configuration.addAnnotatedClass(edu.neu.app.Flight.class);
        configuration.addAnnotatedClass(edu.neu.app.Ticket.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }

    // get all users
    static List<User> getUsers() {
        Session session = getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        System.out.println("Found " + users.size() + " users");
        return users;

    }

    // save an object into database
    static void save(Object obj) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }

    // create a user
    static User createUser(User user) {
        save(user);
        System.out.println("Successfully create " + user.getName());
        return user;
    }

    // get user by id
    static User getUser(Integer id) {
        User user = new User();
        Session session = getSessionFactory().openSession();
        User u = (User) session.load(User.class, id);
        user.setAge(u.getAge());
        user.setMoney(u.getMoney());
        user.setName(u.getName());
        user.setId(u.getId());

        session.close();
        System.out.println("Successfully get " + user.getName());
        return user;
    }

    // delete a user
    static List<User> deleteUser(Integer id) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        User u = (User) session.load(User.class, id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
        return getUsers();
    }

    // update a user's money
    static void updateUserMoney(User u, int price) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, u.getId());
        user.setMoney(user.getMoney() - price);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated the money of " + u.getName());
    }

    // create an Airline
    static Airline createAirLine(Airline airline) {
        save(airline);
        System.out.println("Successfully create " + airline.getName());
        return airline;
    }

    // get airline by id
    static Airline getAirline(Integer id) {
        Airline airline = new Airline();
        Session session = getSessionFactory().openSession();
        Airline a = (Airline) session.load(Airline.class, id);

        airline.setProfit(a.getProfit());
        airline.setId(a.getId());
        airline.setName(a.getName());

        session.close();
        System.out.println("Successfully get " + airline.getName());
        return airline;
    }

    // get all airlines
    static List<Airline> getAirlines() {
        Session session = getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Airline> airlines = session.createQuery("FROM Airline").list();
        session.close();
        System.out.println("Found " + airlines.size() + " airlines");
        return airlines;
    }

    // update an airline's profit after selling a ticket
    static void updateAirlineProfit(Airline a, int price) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Airline airline = (Airline) session.load(Airline.class, a.getId());
        airline.setProfit(airline.getProfit() + price);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated the profit of " + a.getName());
    }

    // create a flight
    static Flight createFlight(Flight flight) {
        save(flight);
        System.out.println("Successfully create " + flight.getId());
        return flight;
    }

    // get flight by id
    static Flight getFlight(Integer id) {
        Flight flight = new Flight();
        Session session = getSessionFactory().openSession();
        Flight f = (Flight) session.load(Flight.class, id);

        flight.setId(f.getId());
        flight.setAirlineId(f.getAirlineId());
        flight.setCapacity(f.getCapacity());
        flight.setDate(f.getDate());
        flight.setDeparture(f.getDeparture());
        flight.setDestination(f.getDestination());
        flight.setPrice(f.getPrice());

        session.close();
        System.out.println("Successfully get the information of" + flight.getId());
        return flight;
    }

    // get all flights
    static List<Flight> getFlights() {
        Session session = getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Flight> flights = session.createQuery("FROM Flight").list();
        session.close();
        System.out.println("Found " + flights.size() + " flights");
        return flights;
    }

    // update the number of passengers enrolled in a flight
    static void upDateFlightEnrolled(Flight flight, int change) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Flight f = (Flight) session.load(Flight.class, flight.getId());
        f.setEnrolled(f.getEnrolled() + change);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated the passengers enrolled in the flight of " + flight.getId());
    }

    // create a ticket
    static Ticket createTicket(Ticket ticket) {
        save(ticket);
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, ticket.getUserId());
        user.getList().add(ticket);
        Airline airline = (Airline) session.load(Airline.class, ticket.getAirlineId());
        airline.getList().add(ticket);
        session.getTransaction().commit();
        session.close();

        System.out.println("Successfully create ticket " + ticket.getId());
        return ticket;
    }

    // get ticket by id
    static Ticket getTicket(Integer id) {
        Ticket ticket = new Ticket();
        Session session = getSessionFactory().openSession();
        Ticket t = (Ticket) session.load(Ticket.class, id);

        ticket.setId(id);
        ticket.setAirlineId(t.getAirlineId());
        ticket.setAirline(t.getAirline());
        ticket.setDate(t.getDate());
        ticket.setDestination(t.getDestination());
        ticket.setDeparture(t.getDeparture());
        ticket.setflightId(t.getFlightId());
        ticket.setUserId(t.getUserId());

        session.close();
        System.out.println("Successfully get the information of " + ticket.getId());
        return ticket;
    }

    // get tickets bought by a user
    static List<Ticket> getTickets(Integer id) {
        List<Ticket> list = null;
        Session session = getSessionFactory().openSession();
        User u = (User) session.load(User.class, id);
        list = u.getList();
        session.close();
        System.out.println("You have " + list.size() + " tickets togeter");
        return list;
    }

    // return back a ticket bought by a user
    static void userTicketRemove(User user, int ticketId) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Ticket t = (Ticket) session.load(Ticket.class, ticketId);
        User u = (User) session.load(User.class, user.getId());
        u.getList().remove(t);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully remove " + ticketId + " from your account.");
    }

    // get tickets sought by an airline
    static List<Ticket> getAirlineTickets(Integer id) {
        List<Ticket> list = null;
        Session session = getSessionFactory().openSession();
        Airline airline = (Airline) session.load(Airline.class, id);
        list = airline.getList();
        session.close();
        System.out.println("We totally sought " + list.size() + " tickets");
        return list;
    }

    // return back a ticket sought by an airline
    static void airlineTicketRemove(Airline airline, int ticketId) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Ticket t = (Ticket) session.load(Ticket.class, ticketId);
        Airline a = (Airline) session.load(Airline.class, airline.getId());
        a.getList().remove(t);
        session.getTransaction().commit();
        session.close();
        System.out.println("The ticket " + ticketId + " has been returned back to our account");
    }


}
