package com.brajesh.cacheingdemo.controller;

import java.util.List;

import com.brajesh.cacheingdemo.modal.Product;
import com.brajesh.cacheingdemo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service)
    {
        this.service = service;
    }

    @GetMapping(path = "/product")
    public List<Product> getProducst() 
    {
        return service.getProducts();
    }

    @GetMapping(path = "/product/{id}")
    public Product getProductById(@PathVariable String id) 
    {   

        return service.getProductById(Integer.parseInt(id));
    }
    
}
