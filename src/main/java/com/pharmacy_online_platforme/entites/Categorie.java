package com.pharmacy_online_platforme.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Produit> produits;
    //L'utilisation des annotations @JsonManagedReference et @JsonBackReference dans Jackson ne limite pas explicitement le nombre de fois qu'un objet peut être affiché dans le JSON.
}
