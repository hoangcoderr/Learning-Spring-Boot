package com.tutorial.apidemo.repositories;

import com.tutorial.apidemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//<Product, Long> = <Entity, Primary Key>
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductName(String productName);
}
