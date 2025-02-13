package it.epicode.gestioneordini.users;

import it.epicode.gestioneordini.orders.Order;
import lombok.Data;

import java.util.List;

@Data
public class Request {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private List<Order> orderList;
}
