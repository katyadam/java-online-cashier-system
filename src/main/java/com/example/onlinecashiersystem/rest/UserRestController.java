package com.example.onlinecashiersystem.rest;

import com.example.onlinecashiersystem.api.UserDto;
import com.example.onlinecashiersystem.data.model.Category;
import com.example.onlinecashiersystem.data.model.ProductPlane;
import com.example.onlinecashiersystem.data.model.User;
import com.example.onlinecashiersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(path = "/{id}/product-planes")
    public ResponseEntity<Set<ProductPlane>> findProductPlanes(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findProductPlanes(id));
    }

    @GetMapping(path = "/{id}/categories")
    public ResponseEntity<Set<Category>> findCategories(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findCategories(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
