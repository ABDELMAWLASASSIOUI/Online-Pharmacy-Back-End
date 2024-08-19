package com.pharmacy_online_platforme.repositories;

import com.pharmacy_online_platforme.entites.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long> {

}