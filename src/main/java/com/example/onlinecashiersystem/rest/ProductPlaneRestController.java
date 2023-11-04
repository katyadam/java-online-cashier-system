package com.example.onlinecashiersystem.rest;

import com.example.onlinecashiersystem.api.ProductPlaneDto;
import com.example.onlinecashiersystem.data.model.Product;
import com.example.onlinecashiersystem.data.model.ProductPlane;
import com.example.onlinecashiersystem.service.api.ProductPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "product-planes")
public class ProductPlaneRestController {

    private final ProductPlaneService productPlaneService;

    @Autowired
    public ProductPlaneRestController(ProductPlaneService productPlaneService) {
        this.productPlaneService = productPlaneService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductPlane> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productPlaneService.findById(id));
    }

    @GetMapping(path = "/{id}/products")
    public ResponseEntity<Set<Product>> findProducts(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productPlaneService.findProducts(id));
    }

    @GetMapping
    public Page<ProductPlane> findAll(Pageable pageable) {
        return productPlaneService.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<ProductPlane> create(@RequestBody ProductPlaneDto productPlaneDto) {
        return ResponseEntity.ok(productPlaneService.createProductPlane(productPlaneDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductPlane> update(
            @PathVariable("id") Long id,
            @RequestBody ProductPlaneDto productPlaneDto
    ) {
        return ResponseEntity.ok(productPlaneService.updateProductPlane(id, productPlaneDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProductPlane> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productPlaneService.deleteProductPlane(id));
    }

}
