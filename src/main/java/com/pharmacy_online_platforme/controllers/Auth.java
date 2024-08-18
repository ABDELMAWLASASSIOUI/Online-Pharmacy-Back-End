package com.pharmacy_online_platforme.controllers;

import com.pharmacy_online_platforme.dto.ReqRes;
import com.pharmacy_online_platforme.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Auth {
    @Autowired
    private AuthService authService;

    @RequestMapping("/signup")
    public ResponseEntity<ReqRes> signup(@RequestBody ReqRes reqRes){
        return ResponseEntity.ok(authService.singnup(reqRes));

    }
}
