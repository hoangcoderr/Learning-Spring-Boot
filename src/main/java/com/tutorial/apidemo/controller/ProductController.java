package com.tutorial.apidemo.controller;


import com.tutorial.apidemo.models.Product;
import com.tutorial.apidemo.models.ResponseObject;
import com.tutorial.apidemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //shows that this is a controller
@RequestMapping(path = "/api/v1/Products")  //this is the path for the controller
public class ProductController {
    //DI = Dependency Injection
    @Autowired //this is a DI
    private ProductRepository repository; // productRepository is created when the application starts


    @GetMapping("")
        //this request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return repository.findAll(); //where is data coming from? from the database
    }
    //must save this to database, now having h2 database = in memory database
    //also use postman to test the api


    //get product by id
    //let's return an objedt with: data, message, status
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Product found", "success", product));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Product not found", "error", ""));
        }
    }


    @PostMapping("/insert")
        //insert a new product
        //postman: Raw, JSON, body
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product product) {
        List<Product> foundProduct = repository.findByProductName(product.getProductName());
        if (foundProduct.size() > 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject("Product already exists", "error", ""));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("Product inserted", "success", repository.save(product)));
    }

    //update, upsert = update if exists, insert if not
    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return repository.findById(id)
                .map(product -> { //if product exists
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Product updated", "success", repository.save(product)));
                })
                .orElseGet(() -> { //if product does not exist
                    newProduct.setId(id);
                    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("Product inserted", "success", repository.save(newProduct)));
                });
    }


    //delete a product
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Product deleted", "success", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Product not found", "error", ""));
        }
    }
}
