package com.pharmacy_online_platforme.services;

import com.pharmacy_online_platforme.dto.ReqRes;
import com.pharmacy_online_platforme.entites.User;
import com.pharmacy_online_platforme.repositories.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes singnup(ReqRes reqRes){

        ReqRes response=new ReqRes();
        try {
            Optional<User> existeUser = authRepo.findByEmail(reqRes.getEmail());
            if (existeUser.isPresent()) {
                response.setMessage("User with this email already exists");
                response.setStatusCode(400);
                return response;
            }
            User ourUsers = new User();
            ourUsers.setEmail(reqRes.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(reqRes.getPassword()));
            ourUsers.setBirth_date(reqRes.getBirth_date());
            ourUsers.setAddress(reqRes.getAddress());

            // Set the role to USER by default, you can also check if it's null or empty first
            ourUsers.setRole("USER");
            User ourUserResult = authRepo.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getId() > 0) {
                response.setOurUsers(ourUserResult);
                response.setMessage("User Saved Successfully");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError("An unexpected error occurred: " + e.getMessage());
        }
        return response;
    }
}
