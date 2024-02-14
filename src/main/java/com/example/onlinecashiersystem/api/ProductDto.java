package com.example.onlinecashiersystem.api;

public record ProductDto(
        String name,
        int price,
        String currency,
        Long productPlaneId

) {
}
