package com.example.onlinecashiersystem.data.repository;

import com.example.onlinecashiersystem.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @NonNull
    Optional<Product> findById(
            @Param("product_id") @NonNull Long id
    );
}
