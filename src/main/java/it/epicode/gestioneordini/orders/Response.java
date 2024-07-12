package it.epicode.gestioneordini.orders;

import it.epicode.gestioneordini.products.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Response {
    private String id;
    private List<Product> productList;
    private float total;
    private LocalDate purchaseDate;
    private LocalDate shippingDate;
    private LocalDate expectedDeliveryDate;
}
