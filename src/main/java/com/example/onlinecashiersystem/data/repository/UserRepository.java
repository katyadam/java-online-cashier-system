package com.example.onlinecashiersystem.data.repository;

import com.example.onlinecashiersystem.data.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    Optional<User> findById(@Param("user_id") @NonNull Long id);

    @NonNull
    Optional<User> findByEmail(@Param("email") @NonNull String email);
}
