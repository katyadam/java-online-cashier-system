package com.example.onlinecashiersystem.service;

import com.example.onlinecashiersystem.data.model.Category;
import com.example.onlinecashiersystem.data.model.ProductPlane;
import com.example.onlinecashiersystem.data.model.User;
import com.example.onlinecashiersystem.data.repository.UserRepository;
import com.example.onlinecashiersystem.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Set<ProductPlane> findProductPlanes(Long id) {
        return findById(id).getProductPlaneSet();
    }

    @Transactional(readOnly = true)
    public Set<Category> findCategories(Long id) {
        return findById(id).getCategorySet();
    }


}
