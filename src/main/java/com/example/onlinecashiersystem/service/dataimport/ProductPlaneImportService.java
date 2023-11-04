package com.example.onlinecashiersystem.service.dataimport;

import com.example.onlinecashiersystem.data.repository.ProductPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveImportedData(Long id, byte[] bytes) {
        productPlaneParser.getParsedCollection(bytes).forEach(
                parsedProductPlane -> {
                    parsedProductPlane.setId(id);
                    productPlaneRepository.save(parsedProductPlane);
                }
        );

    }
}
