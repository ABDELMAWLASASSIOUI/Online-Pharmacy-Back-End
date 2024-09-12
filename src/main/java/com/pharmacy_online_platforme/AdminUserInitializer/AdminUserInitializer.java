package com.pharmacy_online_platforme.AdminUserInitializer;


import com.pharmacy_online_platforme.entites.User;
import com.pharmacy_online_platforme.repositories.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class AdminUserInitializer {

    @Autowired
    private AuthRepo ourUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initializeAdminUser() {
        return args -> {
            // Check if the admin user already exists
            if (!ourUserRepo.findByEmail("abdelmawla@ump.ac.ma").isPresent()) {
                // Create the admin user directly
                User adminUser = new User();
                adminUser.setEmail("abdelmawla@ump.ac.ma");
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                // adminUser.setBirth_date(LocalDate.of(1990, 1, 1));
                adminUser.setAddress("6000 Casa Avenue");
                adminUser.setRole("ADMIN");

                ourUserRepo.save(adminUser);
            }
            if (!ourUserRepo.findByEmail("nabil@ump.ac.ma").isPresent()) {
                // Create the admin user directly
                User adminUser = new User();
                adminUser.setEmail("nabil@ump.ac.ma");
                adminUser.setPassword(passwordEncoder.encode("123456789"));
                // adminUser.setBirth_date(LocalDate.of(1990, 1, 1));
                adminUser.setAddress("6000 Oujda Avenue");
                adminUser.setRole("USER");

                ourUserRepo.save(adminUser);
            }

        };
    }
}
