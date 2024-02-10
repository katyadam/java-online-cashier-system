package com.example.onlinecashiersystem.data.model.auth;

import com.example.onlinecashiersystem.data.model.ProductPlane;
import com.example.onlinecashiersystem.data.model.Transaction;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user", schema = "public")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Getter
    @Column(name = "given_name")
    private String givenName;

    @Getter
    @Column(name = "family_name")
    private String familyName;

    @Getter
    @Column(name = "email")
    private String email;

    @Getter
    @Column(name = "password_hash")
    private String passwordHash;

    @Getter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ProductPlane> productPlaneSet;

    @Getter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    @Getter
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setId(Long id) {
        this.id = id;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setProductPlaneSet(Set<ProductPlane> productPlaneSet) {
        this.productPlaneSet = productPlaneSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Map<String, Object> getMappedUser() {
        return new HashMap<>(Map.of(
                "id", getId(),
                "givenName", getGivenName(),
                "familyName", getFamilyName(),
                "email", getEmail(),
                "password", getPassword(),
                "role", getRole()
        ));
    }
}
