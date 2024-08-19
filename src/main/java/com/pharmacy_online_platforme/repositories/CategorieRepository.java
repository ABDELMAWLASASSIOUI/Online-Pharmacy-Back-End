package com.pharmacy_online_platforme.repositories;

import com.pharmacy_online_platforme.entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}