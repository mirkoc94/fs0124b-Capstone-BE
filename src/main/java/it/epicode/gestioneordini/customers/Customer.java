package it.epicode.gestioneordini.customers;

import it.epicode.gestioneordini.orders.Order;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table (name = "customers")
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(length = 5)
    private String address;
    private String citta;
    private Long cap;
    private String email;
    @Column(length = 20)
    private String password;
    @OneToMany
    private List <Order> orderList;
}
