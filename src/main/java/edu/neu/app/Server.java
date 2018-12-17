package edu.neu.app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

// this class only serves the entry of the app
@Path("/server")
public class Server {

    // this class will perform all the complex functions of the app, such as sorting the result
    private ServerSupport serverSupport = new ServerSupport();

    // get all users
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return serverSupport.getUsers();
    }

    // get user by id
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {
        return serverSupport.getUser(id);
    }

    // create new user
    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        return serverSupport.createUser(user);
    }

    // delete a user
    @DELETE
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> deleteUser(@PathParam("id") int id) {
        return serverSupport.deleteUser(id);
    }

    // browse all flights
    @GET
    @Path("/flights")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getFlights() {
        return serverSupport.getFlights();
    }

    // get flight by id
    @GET
    @Path("/flights/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight getFlight(@PathParam("id") int id) {
        return serverSupport.getFlight(id);
    }

    // search for a flight
    @GET
    @Path("/flights/{date}/{dep}/{dest}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> searchFlights(@PathParam("date") String date, @PathParam("dep") String dep, @PathParam("dest") String dest) {
        return serverSupport.searchFlights(date, dep, dest);
    }

    // create a flight
    @POST
    @Path("/flights")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Flight createFlight(Flight flight) {
        return serverSupport.createFlight(flight);
    }

    // browse tickets bought of a user
    @GET
    @Path("/users/{id}/ticket")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTickets(@PathParam("id") int id) {
        return serverSupport.getTickets(id);
    }

    // browse tickets sought of an airline
    @GET
    @Path("/airlines/{id}/ticket")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getAirlineTickets(@PathParam("id") int id) {
        return serverSupport.getAirlineTickets(id);
    }

    // buy a ticket
    @POST
    @Path("/ticket")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket createTicket(Ticket ticket) {
        return serverSupport.createTicket(ticket);
    }

    // refund of a ticket
    @PUT
    @Path("/ticket/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> refundTicket(@PathParam("id") int id) {
        return serverSupport.refundTicket(id);
    }

    // get all airlines
    @GET
    @Path("/airlines")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Airline> getAirlines() {
        return serverSupport.getAirlines();
    }

    // create new airline
    @POST
    @Path("/airlines")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Airline createAirline(Airline airline) {
        return serverSupport.createAirline(airline);
    }

    // get airline by id
    @GET
    @Path("/airlines/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Airline getAirline(@PathParam("id") int id) {
        return serverSupport.getAirline(id);
    }
}
