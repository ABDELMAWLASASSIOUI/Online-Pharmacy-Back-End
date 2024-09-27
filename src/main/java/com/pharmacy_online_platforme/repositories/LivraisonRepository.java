package com.pharmacy_online_platforme.repositories;


import com.pharmacy_online_platforme.entites.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    // Ajoutez des méthodes de requête personnalisées si nécessaire
}
