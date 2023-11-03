package com.example.onlinecashiersystem.api;

public record UserDto(
        String givenName,
        String familyName,
        String email
) {
}
