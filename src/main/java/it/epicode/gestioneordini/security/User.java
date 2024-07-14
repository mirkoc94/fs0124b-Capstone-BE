package it.epicode.gestioneordini.security;

import it.epicode.gestioneordini.orders.Order;
import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    private String username;
    private String email;
    @Column(length = 125, nullable = false)
    private String password;
    @OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderList;
    @ManyToMany(fetch = FetchType.EAGER)
    private final List<Roles> roles = new ArrayList<>();
}
