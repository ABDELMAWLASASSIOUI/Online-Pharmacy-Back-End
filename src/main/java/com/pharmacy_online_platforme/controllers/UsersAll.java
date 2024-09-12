package com.pharmacy_online_platforme.controllers;


import com.pharmacy_online_platforme.entites.User;
import com.pharmacy_online_platforme.repositories.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersAll {

    @Autowired
    private AuthRepo authRepo;
    @GetMapping("/admin/getAllusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = authRepo.findAll();
        return ResponseEntity.ok(users);
    }
}
