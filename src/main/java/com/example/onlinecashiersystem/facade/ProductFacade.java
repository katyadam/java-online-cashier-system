package com.example.onlinecashiersystem.facade;

import com.example.onlinecashiersystem.api.ProductDto;
import com.example.onlinecashiersystem.data.model.Product;
import com.example.onlinecashiersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductFacade {

    private final ProductService productService;

    @Autowired
    public ProductFacade(ProductService productService) {
        this.productService = productService;
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productService.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    public Product createProduct(ProductDto productDto) {
        return productService.createProduct(productDto);
    }
}
