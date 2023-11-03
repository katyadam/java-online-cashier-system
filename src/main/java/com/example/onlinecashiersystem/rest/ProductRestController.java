package com.example.onlinecashiersystem.rest;

import com.example.onlinecashiersystem.data.model.Product;
import com.example.onlinecashiersystem.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/products")
public class ProductRestController {

    private final ProductFacade productFacade;

    @Autowired
    public ProductRestController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productFacade.findById(id));
    }

    @GetMapping
    public Page<Product> findAll(Pageable pageable) {
        return productFacade.findAll(pageable);
    }
}
