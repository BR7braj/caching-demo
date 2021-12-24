package com.brajesh.cacheingdemo.service;

import java.util.List;
import java.util.Optional;

import com.brajesh.cacheingdemo.modal.Product;
import com.brajesh.cacheingdemo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private ProductRepository repository;

    @Autowired
    public ProductService(final ProductRepository repository)
    {
        this.repository = repository;
    }

    @Cacheable(value = "Product")
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Cacheable(value = "Product" , key = "#id")
    public Product getProductById(int id)
    {
        return Optional.ofNullable(repository.findById(id)).get().get();
    }
}
