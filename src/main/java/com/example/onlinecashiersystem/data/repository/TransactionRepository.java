package com.example.onlinecashiersystem.data.repository;

import com.example.onlinecashiersystem.data.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @NonNull
    Optional<Transaction> findById(
            @Param("transaction_id") @NonNull Long id
    );
}
