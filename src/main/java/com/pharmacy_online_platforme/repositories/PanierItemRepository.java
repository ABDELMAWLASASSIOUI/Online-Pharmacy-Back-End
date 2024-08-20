package com.pharmacy_online_platforme.repositories;


import com.pharmacy_online_platforme.entites.PanierItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierItemRepository extends JpaRepository<PanierItem,Long> {
}
