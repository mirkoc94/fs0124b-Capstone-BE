package it.epicode.gestioneordini.products;

import lombok.Data;

@Data
public class Response {
    private String id;
    private String brand;
    private String name;
    private float price;
    private String image;
}
