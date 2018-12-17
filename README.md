# bookingApp
This app is for searching and buying flight tickets. Maven is used to create the project. Jersey frame work is applied to deal with restful requests and build the server.  Hibernate is used to handle the date layer and communicate with mySQL database.

The project structure is based on several layers. Server layer will only dealing with http requests. ServerSupport layer will operate as main server and deal with more complex functions such as sorting and filtering. DAO layer is hibernate layer which will only operate hibernate functions and communicate with mySQL. (If we don’t want to use database, we can use UserDAO layer to store data into ConcurrentHashMap).

User have flowing functions:1 create a user (provide basic information and put money into account)2 view all the flights3 search flights based on “date, departure place, 4 buy a ticket5 view all the tickets bought6 refund a ticket7 view current money in the account8 delete user

Airline have the flowing functions:1 create an airline2 create a flight3 view all the flights it have4 view all the tickets it sought5 view current profit

Flight class will record all the needed information of a single flight, such as the departure date, the departure place and destination. The main function is to check enrolled number. Once a flight is already full, this flight won’t be shown on user’s search.

When buying a ticket, firstly user must have enough money to buy the ticket (user.money > ticket.price) , money will then be deduct from user’s account and will be added to airline’s 
profit. The OneToMany mapping : user and ticket, airline and ticket will be updated. The flight will have a new passenger enrolled.

When refund a ticket, money will then be put back to user’s account and will be deducted from  airline’s profit. The OneToMany mapping : user and ticket, airline and ticket will be updated. The flight will have a new spare seat to sell. 
