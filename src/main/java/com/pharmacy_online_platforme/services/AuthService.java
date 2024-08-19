package com.pharmacy_online_platforme.services;

import com.pharmacy_online_platforme.dto.ReqRes;
import com.pharmacy_online_platforme.entites.User;
import com.pharmacy_online_platforme.repositories.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthRepo ourUserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;

    public ReqRes singnup(ReqRes reqRes) {

        ReqRes response = new ReqRes();
        try {
            Optional<User> existeUser = ourUserRepo.findByEmail(reqRes.getEmail());
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
            ourUsers.setName(reqRes.getName());

            // Set the role to USER by default, you can also check if it's null or empty first
            ourUsers.setRole("USER");
            User ourUserResult = ourUserRepo.save(ourUsers);
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

    public ReqRes signIn(ReqRes signinRequest) {
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

            var user = ourUserRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken((UserDetails) user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), (UserDetails) user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        User users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), (UserDetails) users)) {
            var jwt = jwtUtils.generateToken((UserDetails) users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
