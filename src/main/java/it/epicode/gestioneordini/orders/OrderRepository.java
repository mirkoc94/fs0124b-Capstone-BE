package it.epicode.gestioneordini.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findByCustomerEmail(String email);
    List<Order> findByUserId(Long idUser);

}


//guardare pure il service