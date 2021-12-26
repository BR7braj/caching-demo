package com.brajesh.cacheingdemo.service;

import java.util.List;
import java.util.Optional;

import com.brajesh.cacheingdemo.modal.Product;
import com.brajesh.cacheingdemo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    @Cacheable(value = "Product"  , key = "#id")
    public Product getProductById(int id)
    {
        return Optional.ofNullable(repository.findById(id)).get().get();
    }

    public Product saveProduct(Product product)
    {
        return repository.save(product);
    }

    @CachePut(value = "Product" , key = "#id")
    public Product updateProduct(Product product, int id) throws Exception {
        Product savedProduct = repository.findById(id).orElseThrow(() -> new Exception());
        savedProduct.setId(product.getId());
        savedProduct.setName(product.getName());
        return repository.save(savedProduct);

    }

    @CacheEvict(value = "Product"  , key = "#id")
    public void deleteProduct(String id) throws NumberFormatException, Exception {
        Product product = repository.findById(Integer.parseInt(id)).orElseThrow(() ->  new Exception());
        repository.delete(product);
    }

}
