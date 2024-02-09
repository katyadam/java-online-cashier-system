package com.example.onlinecashiersystem.service.api;

import com.example.onlinecashiersystem.api.UserDto;
import com.example.onlinecashiersystem.data.model.ProductPlane;
import com.example.onlinecashiersystem.data.model.auth.User;
import com.example.onlinecashiersystem.data.repository.UserRepository;
import com.example.onlinecashiersystem.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with userId " + id + " was not found!"));
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Set<ProductPlane> findProductPlanes(Long id) {
        return findById(id).getProductPlaneSet();
    }

    @Transactional
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setGivenName(userDto.givenName());
        user.setFamilyName(user.getFamilyName());
        user.setEmail(user.getEmail());
        user.setPasswordHash(userDto.passwordHash());

        userRepository.save(user);
        return user;
    }

    @Transactional
    public User updateUser(Long id, UserDto userDto) {
        User toUpdate = findById(id);
        toUpdate.setGivenName(userDto.givenName());
        toUpdate.setFamilyName(userDto.familyName());
        toUpdate.setEmail(userDto.email());
        toUpdate.setPasswordHash(userDto.passwordHash());

        userRepository.save(toUpdate);
        return toUpdate;
    }

    @Transactional
    public User deleteUser(Long id) {
        User toDelete = findById(id);
        userRepository.delete(toDelete);

        return toDelete;
    }
}
