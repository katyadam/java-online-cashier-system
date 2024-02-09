package com.example.onlinecashiersystem.auth;

import com.example.onlinecashiersystem.data.model.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String givenName;
    private String familyName;
    private String email;
    private String password;
    private Role role;

}
