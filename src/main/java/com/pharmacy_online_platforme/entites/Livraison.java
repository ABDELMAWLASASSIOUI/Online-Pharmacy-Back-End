package com.pharmacy_online_platforme.entites;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "livraison")
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // L'utilisateur qui reçoit la livraison

    @OneToOne
    @JoinColumn(name = "panier_id")
    private Panier panier;  // Le panier contenant les produits

    private String address;  // L'adresse de livraison
    private String name;  // Le nom du destinataire
    private String phoneNumber;  // Numéro de téléphone du destinataire
    private String deliveryMethod;  // Méthode de livraison (ex: express, standard)
    private String status;  // Statut de la livraison (en cours, livrée, annulée)
    private Date deliveryDate;
}
