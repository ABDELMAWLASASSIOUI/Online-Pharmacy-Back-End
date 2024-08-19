package com.pharmacy_online_platforme.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminTesting {

    @GetMapping("/api/admin/get")
    public String testing()
    {
        return "hello role admin";
    }
    @GetMapping("/api/user/get")
    public String testing1()
    {
        return "hello role user";
    }

}
