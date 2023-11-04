package com.example.onlinecashiersystem.service.dataimport;

import com.example.onlinecashiersystem.data.model.ProductPlane;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

// line format: ProductPlane::name;...
@Component
public class ProductPlaneParser implements Parser<ProductPlane> {

    public static final String SEPARATOR = ";";

    @Override
    public Collection<ProductPlane> getParsedCollection(byte[] bytes) {
        String input = new String(bytes);
        return Arrays.stream(input.split(System.lineSeparator()))
                .map(this::parseLine)
                .toList();
    }

    private ProductPlane parseLine(String line) {
        String[] productPlaneParams = line.split(SEPARATOR);
        ProductPlane productPlane = new ProductPlane();
        productPlane.setName(productPlaneParams[0]);
        return productPlane;
    }

}
