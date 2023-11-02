package com.example.onlinecashiersystem.config;

import com.example.onlinecashiersystem.data.model.Product;
import com.example.onlinecashiersystem.data.model.ProductPlane;
import com.example.onlinecashiersystem.data.model.User;
import com.example.onlinecashiersystem.data.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class InsertInitialDataService {

    private final ProductRepository productRepository;

    @Autowired
    public InsertInitialDataService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void insertDummyData() {
        insertProductData();
    }

    private void insertProductData() {
        User user = new User();
        user.setId(1L);
        user.setGivenName("John");
        user.setFamilyName("Doe");
        user.setEmail("johndoe@gmail.com");
        user.setPasswordHash("abcd521e");
        Product p = new Product();
        p.setId(1L);
        p.setName("Kebab");

        Product p2 = new Product();
        p.setId(2L);
        p.setName("Burrito");

        Product p3 = new Product();
        p.setId(3L);
        p.setName("McChicken");


        ProductPlane productPlane = new ProductPlane();
        productPlane.setId(1L);
        productPlane.setName("Fast food");

//        productRepository.save(p);
//        productRepository.save(p2);
//        productRepository.save(p3);

    }
}
