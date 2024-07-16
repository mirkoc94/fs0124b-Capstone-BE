package it.epicode.gestioneordini.orders;

import it.epicode.gestioneordini.products.Product;
import it.epicode.gestioneordini.users.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Response {
    private Long id;
    private User user;
    private List<Product> productList;
    private float total;
    private LocalDate purchaseDate;
    private LocalDate shippingDate;
    private LocalDate expectedDeliveryDate;
}
