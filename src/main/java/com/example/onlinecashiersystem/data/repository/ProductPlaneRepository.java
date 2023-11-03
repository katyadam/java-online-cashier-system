package com.example.onlinecashiersystem.data.repository;

import com.example.onlinecashiersystem.data.model.ProductPlane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPlaneRepository extends JpaRepository<ProductPlane, Long> {
    @NonNull
    Optional<ProductPlane> findById(
            @Param("product_plane_id") @NonNull Long id
    );

}
