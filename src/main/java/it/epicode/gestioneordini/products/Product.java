package it.epicode.gestioneordini.products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String brand;
    private String name;
    private float price;
    private String image;
    private Long quantity;
    private Long partialTotal;
}
