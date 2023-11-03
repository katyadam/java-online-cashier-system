package com.example.onlinecashiersystem.data.repository;

import com.example.onlinecashiersystem.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @NonNull
    Optional<Category> findById(
            @Param("category_id") @NonNull Long id
    );
}
