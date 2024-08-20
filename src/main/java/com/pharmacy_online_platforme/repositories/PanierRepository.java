package com.pharmacy_online_platforme.repositories;

import com.pharmacy_online_platforme.entites.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanierRepository extends JpaRepository<Panier,Long> {
    Optional<Panier> findByUserId(Long userId);
}
