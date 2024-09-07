package com.tutorial.apidemo.database;

import com.tutorial.apidemo.models.Product;
import com.tutorial.apidemo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product product1 = new Product("Macbook Pro", 2020, 2000.0, "https://www.apple.com/macbook-pro-13/");
                Product product2 = new Product("Macbook Air", 2020, 1000.0, "https://www.apple.com/macbook-air-13/");
                Product product3 = new Product("Ipad Air Green", 2019, 1800.0, "https://www.apple.com/macbook-pro-16/");
                logger.info("Inserting data in the database" + productRepository.save(product1));
                logger.info("Inserting data in the database" + productRepository.save(product2));
                logger.info("Inserting data in the database" + productRepository.save(product3));
            }
        };
    }
}
