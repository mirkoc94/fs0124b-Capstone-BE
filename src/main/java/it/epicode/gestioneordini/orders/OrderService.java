package it.epicode.gestioneordini.orders;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    // GET ALL
    public List<Order> findAll(){
        return repository.findAll();
    }

    // GET
    public Response findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Ordine non trovato");
        }
        Order entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public List<Order> findByUserId(Long idUser) {
        return repository.findByUserId(idUser);
    }

    // POST
    public Response create(Request request){
        Order entity = new Order();
        BeanUtils.copyProperties(request, entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Ordine non trovato");
        }
        Order entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //DELETE
    public String delete(Long id){
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Ordine non trovato");
        }
        repository.deleteById(id);
        return "Order eliminato";
    }
}
