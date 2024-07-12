package it.epicode.gestioneordini.customers;

import it.epicode.gestioneordini.orders.Order;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String cap;
    private String address;
    private String email;
    private List <Order> orderList;
}
