package com.example.onlinecashiersystem.service.api;

import com.example.onlinecashiersystem.api.ProductDto;
import com.example.onlinecashiersystem.data.model.Product;
import com.example.onlinecashiersystem.data.repository.ProductRepository;
import com.example.onlinecashiersystem.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductPlaneService productPlaneService;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            ProductPlaneService productPlaneService,
            CategoryService categoryService
    ) {
        this.productRepository = productRepository;
        this.productPlaneService = productPlaneService;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with userId: " + id + " was not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product createProduct(ProductDto productDto) {
        Product newProduct = new Product();
        newProduct.setName(productDto.name());
        newProduct.setProductPlane(productPlaneService.findById(productDto.productPlaneId()));
        newProduct.setCategory(categoryService.findById(productDto.categoryId()));

        productRepository.save(newProduct);
        return newProduct;
    }

    @Transactional
    public Product updateProduct(Long id, ProductDto productDto) {
        Product toUpdate = findById(id);
        toUpdate.setName(productDto.name());
        toUpdate.setCategory(categoryService.findById(productDto.categoryId()));

        productRepository.save(toUpdate);
        return toUpdate;
    }

    @Transactional
    public Product deleteProduct(Long id) {
        Product toDelete = findById(id);
        productRepository.delete(toDelete);

        return toDelete;
    }

}
