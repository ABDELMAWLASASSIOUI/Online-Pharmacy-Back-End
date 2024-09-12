package com.pharmacy_online_platforme.entites;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)//changer this ligne lazy for eager
    @JoinColumn(name = "categorie_id", nullable = false)
    @JsonBackReference
    private Categorie categorie;
    @OneToOne
    @JoinColumn(name = "image_id")  // Colonne qui contient l'ID de l'image associée
    private Image image;  // Chaque profil a une image unique associée
}
