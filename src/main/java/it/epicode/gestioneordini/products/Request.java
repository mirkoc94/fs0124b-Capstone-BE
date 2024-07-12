package it.epicode.gestioneordini.products;

import lombok.Data;

@Data
public class Request {
    private String brand;
    private String name;
    private float price;
    private String image;
}
