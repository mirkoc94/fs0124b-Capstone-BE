package it.epicode.gestioneordini.customers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    // GET ALL
    public List<Customer> findAll(){
        return repository.findAll();
    }

    // GET
    public Response findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Customer non trovato");
        }
        Customer entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Customer findByEmail(String email){
        return repository.findByEmail(email);
    }

    // POST
    public Response create(Request request){
        Customer entity = new Customer();
        BeanUtils.copyProperties(request, entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Customer non trovato");
        }
        Customer entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //DELETE
    public String delete(Long id){
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Customer non trovato");
        }
        // Se il Customer esiste, viene eliminato dal database.
        repository.deleteById(id);
        return "Customer eliminato";
    }
}
