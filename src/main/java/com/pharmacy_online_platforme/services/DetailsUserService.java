package com.pharmacy_online_platforme.services;


import com.pharmacy_online_platforme.repositories.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsUserService  implements UserDetailsService {
    @Autowired
    private AuthRepo authRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) authRepo.findByEmail(username).orElseThrow();
    }
}
