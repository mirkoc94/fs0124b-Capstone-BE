package it.epicode.gestioneordini.orders;

import it.epicode.gestioneordini.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService service;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    //@GetMapping("/users/{userId}/orders")
    //public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
    //    it.epicode.gestioneordini.customers.Response user = userService.findById(userId);
    //    if (user == null) {
    //        return ResponseEntity.notFound().build();
    //    }
    //    List<Order> orders = service.getUserOrders(userId);
    //    return ResponseEntity.ok(orders);
    //}

    //@GetMapping("/users/{userId}/orders")
    //public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
    //    // Recupera l'utente corrente dall'oggetto di sicurezza
    //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //    String currentPrincipalName = authentication.getName();
//
    //    // Verifica se l'utente corrente ha accesso agli ordini richiesti
    //    if (!currentPrincipalName.equals(userId.toString())) {
    //        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    //    }
//
    //    // Recupera gli ordini dell'utente specificato
    //    List<Order> orders = service.getUserOrders(userId);
    //    return ResponseEntity.ok(orders);
    //}

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Request request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> modify(@PathVariable Long id, @RequestBody Request request){
        return ResponseEntity.ok(service.modify(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
