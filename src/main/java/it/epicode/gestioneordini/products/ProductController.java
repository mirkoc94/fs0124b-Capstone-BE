package it.epicode.gestioneordini.products;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    private ProductRepository productsRepository;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Request request){
        return ResponseEntity.ok(service.create(request));
    }

    //@PostMapping
    //public ResponseEntity<Response> createProduct(@RequestParam("brand") String brand,
    //                                              @RequestParam("name") String name,
    //                                              @RequestParam("price") float price,
    //                                              @RequestParam("image") MultipartFile image,
    //                                              @RequestParam("quantity") Long quantity,
    //                                              @RequestParam("partialTotal") Long partialTotal) {
    //    try {
    //        String imageUrl = service.uploadImage(image);
//
    //        Request request = new Request();
    //        request.setBrand(brand);
    //        request.setName(name);
    //        request.setPrice(price);
    //        request.setImage(imageUrl);
//
    //        Response response = service.create(request);
    //        return ResponseEntity.ok(response);
    //    } catch (IOException e) {
    //        return ResponseEntity.status(500).body(null);
    //    }
    //}

    @PutMapping("/{id}")
    public ResponseEntity<Response> modify(@PathVariable Long id, @RequestBody Request request){
        return ResponseEntity.ok(service.modify(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    //CLOUDINARY

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@PathVariable String name, @RequestParam("file") MultipartFile file) {
        try {
            var uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    com.cloudinary.utils.ObjectUtils.asMap("public_id", name + "_image"));

            String url = uploadResult.get("url").toString();

            Optional<Product> productOptional = productsRepository.findOneByName(name);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                productsRepository.save(product);
                return ResponseEntity.ok(url);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/uploa")
    public ResponseEntity<String> getProductImage(@PathVariable String name) {
        Optional<Product> product = productsRepository.findOneByName(name);
        if (product.isPresent() && product.get().getImage() != null) {
            return ResponseEntity.ok(product.get().getImage());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        }
    }


}
