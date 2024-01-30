package com.example.onlinecashiersystem.api;

public record TransactionDto(
        int amount,
        Long productId
) {
}
