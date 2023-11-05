package com.example.onlinecashiersystem.service.dataimport;

import com.example.onlinecashiersystem.data.repository.ProductPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductPlaneImportService {

    private final ProductPlaneRepository productPlaneRepository;
    private final ProductPlaneParser productPlaneParser;

    @Autowired
    public ProductPlaneImportService(
            ProductPlaneRepository productPlaneRepository,
            ProductPlaneParser productPlaneParser
    ) {
        this.productPlaneRepository = productPlaneRepository;
        this.productPlaneParser = productPlaneParser;
    }

    @Transactional
    public void importData(Long id, MultipartFile file) {
        try {
            productPlaneParser.getParsedCollection(file.getBytes()).forEach(
                    parsedProductPlane -> {
                        parsedProductPlane.setId(id);
                        productPlaneRepository.save(parsedProductPlane);
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
