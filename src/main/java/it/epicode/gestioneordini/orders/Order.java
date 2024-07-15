package it.epicode.gestioneordini.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.gestioneordini.products.Product;
import it.epicode.gestioneordini.security.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnoreProperties({"orderList","id"})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany
    private List <Product> productList;
    private float total;
    private LocalDate purchaseDate;
    private LocalDate shippingDate;
    private LocalDate expectedDeliveryDate;

}
