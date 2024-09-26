package com.pharmacy_online_platforme.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pharmacy_online_platforme.Enum.PanierStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference // Ajoutez cette ligne pour gérer la sérialisation
    private User user;
    @OneToMany(mappedBy = "panier",cascade = CascadeType.ALL)
    private List<PanierItem> items;
    @Transient
    private double totalPrice;
    @Transient
    private double fraisLivraison = 20.00;  // Exemple de frais de livraison fixe

    @Enumerated(EnumType.STRING)
    private PanierStatus status = PanierStatus.EN_COURS; // Nouveau statut ajou
    public double getTotalPrice() {
        return items.stream().mapToDouble(PanierItem::getTotalPrice).sum();
    }
    public void setTotalPrice() {
        this.totalPrice = getTotalPrice(); // Recalcule le prix total
    }
    public double getTotalPriceAvecLivraison() {
        return getTotalPrice() + fraisLivraison;
    }

// @JsonManagedReference et @JsonBackReference sont utilisés ensemble pour éviter les boucles infinies dans les relations bidirectionnelles.
    // @JsonManagedReference(value = "panier-panierItem")//éviter des boucles infinies lors de la sérialisation de relations parents-enfants dans les objets Java en JSON
}
