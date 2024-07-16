package it.epicode.gestioneordini.products;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.gestioneordini.security.FileSizeExceededException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    private Cloudinary cloudinary;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    // GET ALL
    public List<Product> findAll(){
        return repository.findAll();
    }

    // GET
    public Response findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Prodotto non trovato");
        }
        Product entity = repository.findById(id).get();
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public List<Product> findByBrand(String brand){
        return repository.findByBrand(brand);
    }

    public List<Product> findByName(String name){
        return repository.findByName(name);
    }

    // POST
    public Response create(Request request){
        Product entity = new Product();
        BeanUtils.copyProperties(request, entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, Request request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Prodotto non trovato");
        }
        Product entity = repository.findById(id).get();
        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //DELETE
    public String delete(Long id){
        if(!repository.existsById(id)){
            throw  new EntityNotFoundException("Prodotto non trovato");
        }
        repository.deleteById(id);
        return "Prodotto eliminato";
    }

    //Cloudinary

    @Transactional
    public String uploadImage(Long id, MultipartFile image) throws IOException {
        long maxFileSize = getMaxFileSizeInBytes();
        if (image.getSize() > maxFileSize) {
            throw new FileSizeExceededException("File size exceeds the maximum allowed size");
        }

        Optional<Product> optionalProduct = repository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        String existingPublicId = product.getImage();
        if (existingPublicId != null && !existingPublicId.isEmpty()) {
            cloudinary.uploader().destroy(existingPublicId, ObjectUtils.emptyMap());
        }

        Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        String publicId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("url");

        product.setImage(publicId);
        repository.save(product);

        return url;
    }


// DELETE delete cloudinary file

    @Transactional
    public String deleteImage(Long id) throws IOException {
        Optional<Product> optionalProduct = repository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        String publicId = product.getImage();
        if (publicId != null && !publicId.isEmpty()) {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            product.setImage(null);
            repository.save(product);
            return "Image deleted successfully";
        } else {
            return "No image found for deletion";
        }
    }


    // PUT update cloudinary file
    @Transactional
    public String updateImage(Long id, MultipartFile updatedImage) throws IOException {
        deleteImage(id);
        return uploadImage(id, updatedImage);
    }

    public long getMaxFileSizeInBytes() {
        String[] parts = maxFileSize.split("(?i)(?<=[0-9])(?=[a-z])");
        long size = Long.parseLong(parts[0]);
        String unit = parts[1].toUpperCase();
        switch (unit) {
            case "KB":
                size *= 1024;
                break;
            case "MB":
                size *= 1024 * 1024;
                break;
            case "GB":
                size *= 1024 * 1024 * 1024;
                break;
        }
        return size;
    }
}
