package com.tutorial.apidemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    //this is the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto increment
    private Long id;
    private String productName;
    private int productYear;
    private Double price;
    private String url;
    public Product() {
    }

    //default constructor
    public Product(String productName, int year, Double price, String url) {
        this.productName = productName;
        this.productYear = year;
        this.price = price;
        this.url = url;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return productYear;
    }

    public void setYear(int year) {
        this.productYear = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + productYear +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }


}
