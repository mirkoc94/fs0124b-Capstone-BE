package it.epicode.gestioneordini.customers;

import it.epicode.gestioneordini.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Customer findByEmail(String email);
}
