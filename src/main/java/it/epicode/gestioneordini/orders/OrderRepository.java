package it.epicode.gestioneordini.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findByUserEmail(String email);
    public List<Order> findByUserId(Long userId);

}


//guardare pure il service