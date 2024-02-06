package com.example.onlinecashiersystem.service.dataimport.service;

import com.example.onlinecashiersystem.api.ProductDto;
import com.example.onlinecashiersystem.service.api.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// name,price,currency\n
@Service
public class ProductImportService {

    private final ProductService productService;

    public ProductImportService(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    public void importData(Long productPlaneId, MultipartFile file) {
        try {
            String stringData = new String(file.getBytes());
            for (String line : stringData.split(System.lineSeparator())) {
                String[] attr = line.split(",");
                productService.createProduct(new ProductDto(attr[0], Integer.parseInt(attr[1]), attr[2], productPlaneId));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
