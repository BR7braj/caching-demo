package com.brajesh.cacheingdemo.controller;

import java.util.List;

import com.brajesh.cacheingdemo.modal.Product;
import com.brajesh.cacheingdemo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping(path = "/product")
    public Product saveProduct(@RequestBody Product product) {
        
        return service.saveProduct(product);
    }

    @PutMapping(path ="/product/modify/{id}")
    public Product updateProduct(@RequestBody Product product , @PathVariable String id) throws Exception {
        return service.updateProduct(product , Integer.parseInt(id));
    }

    @DeleteMapping(path = "/product/delete/{id}")
    public String deleteProduct(@PathVariable String id) throws NumberFormatException, Exception {
        service.deleteProduct(id);
        return String.format("Deleted product with id : %s", id);
    }
    
    
}
