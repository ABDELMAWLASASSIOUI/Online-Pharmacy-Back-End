package com.pharmacy_online_platforme.controllers;

import com.pharmacy_online_platforme.dto.ReqRes;
import com.pharmacy_online_platforme.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Auth {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signup(@RequestBody ReqRes reqRes){
        return ResponseEntity.ok(authService.singnup(reqRes));
    }
    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        SecurityContextHolder.clearContext();//efface le contexte de sécurité actuel
        request.getSession().invalidate();

        Map<String, String> response = new HashMap<>();
        response.put("message", "You have successfully logged out.");
        response.put("token", token != null ? token : "No token provided");

        return ResponseEntity.ok(response);
    }

}
