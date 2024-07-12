package it.epicode.gestioneordini.products;

import it.epicode.gestioneordini.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByBrand(String brand);
    public List<Product> findByName(String name);

    Optional<Product> findOneByName(String name);
    boolean existsByName(String name);
}
