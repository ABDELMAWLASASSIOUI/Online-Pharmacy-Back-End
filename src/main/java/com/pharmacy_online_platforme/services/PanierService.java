package com.pharmacy_online_platforme.services;

import com.pharmacy_online_platforme.Enum.PanierStatus;
import com.pharmacy_online_platforme.dto.PanierDTO;
import com.pharmacy_online_platforme.entites.Panier;
import com.pharmacy_online_platforme.entites.PanierItem;
import com.pharmacy_online_platforme.entites.Produit;
import com.pharmacy_online_platforme.entites.User;
import com.pharmacy_online_platforme.repositories.AuthRepo;
import com.pharmacy_online_platforme.repositories.PanierItemRepository;
import com.pharmacy_online_platforme.repositories.PanierRepository;
import com.pharmacy_online_platforme.repositories.ProduitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PanierService {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private PanierItemRepository panierItemRepository;
    @Autowired
    private AuthRepo authRepo;
    public Panier addItemToPanier(Long panierId, Long produitId, int quantity) { //is work
        if (panierId == null || produitId == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        Optional<Panier> optionalPannier = panierRepository.findById(panierId);
        Optional<Produit> optionalProduct = produitRepository.findById(produitId);

        if (optionalPannier.isPresent() && optionalProduct.isPresent()) {
            Panier panier = optionalPannier.get();
            Produit produit = optionalProduct.get();

            PanierItem item = new PanierItem();
            item.setProduit(produit);
            item.setPanier(panier);
            item.setQuantity(quantity);

            panier.getItems().add(item);

            return panierRepository.save(panier);
        }

        return null;
    }
   /* public Panier createPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    */
   public Panier createPanier(Long userId) {
       Optional<Panier> existingPanier = panierRepository.findByUserId(userId);//the reson of content OneTonOne
       if (existingPanier.isPresent()) {
           // Panier déjà existant, vous pouvez lancer une exception ou simplement retourner le panier existant
           throw new IllegalArgumentException("Le panier pour l'utilisateur avec ID " + userId + " existe déjà.");
       }
       Panier newPanier = new Panier();
       newPanier.setUser(authRepo.findById(Math.toIntExact(userId)).orElseThrow());
       return panierRepository.save(newPanier);
   }

   public PanierDTO createPanierDTO(PanierDTO panierDTO)
   {
       Panier panier=new Panier();
       User user=authRepo.findById(panierDTO.getUserId()).orElseThrow(()->new RuntimeException("the id of user not existe"));
       panier.setUser(user);
       panierRepository.save(panier);
       return convertDTO(panier);
   }
   public PanierDTO convertDTO(Panier panier)
   {
       PanierDTO panierDTO=new PanierDTO();
       panierDTO.setUserId(panier.getUser().getId());
       return panierDTO;
   }


    //remove Item
    @Transactional
    public Panier removeItemById(Long itemId) {
        Optional<PanierItem> optionalItem = panierItemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            PanierItem itemToRemove = optionalItem.get();
            Panier panier = itemToRemove.getPanier(); // Récupère le panier associé

            panier.getItems().remove(itemToRemove); // Supprime l'élément du panier
            panierItemRepository.deleteById(itemId); // Supprime l'élément de la base de données
            panierRepository.save(panier); // Met à jour le panier

            return panier;
        } else {
            throw new RuntimeException("Article non trouvé");
        }

    }

    private static final Logger logger = Logger.getLogger(PanierService.class.getName());

    public double calculerTotalPrixPanier(Long panierId) {
        Optional<Panier> optionalPanier = panierRepository.findById(panierId);
        if (optionalPanier.isPresent()) {
            Panier panier = optionalPanier.get();
            double totalPrice = panier.getTotalPrice();
            logger.info("Total price for Panier ID " + panierId + " is " + totalPrice);
            return totalPrice;
        } else {
            logger.warning("Panier ID " + panierId + " not found.");
            return 0.0;
        }
    }
//get:user,product,pricetotale
   /* public Panier getPanierById(Long panierId) {
        Optional<Panier> optionalPanier = panierRepository.findById(panierId);
        if (optionalPanier.isPresent()) {
            return optionalPanier.get();
        } else {
            throw new RuntimeException("Panier non trouvé avec l'ID " + panierId);
        }
    }

    */
public Panier getPanierById(Long id) {
    Optional<Panier> optionalPanier = panierRepository.findById(id);
    if (optionalPanier.isPresent()) {
        Panier panier = optionalPanier.get();
        // Assurez-vous que l'utilisateur est chargé (si nécessaire, utilisez fetch join ou EntityGraph)
        if (panier.getUser() != null) {
            // L'utilisateur est chargé correctement
            return panier;
        }
    }
    throw new RuntimeException("Panier not found");
}


    public List<Panier> getPaniersByUserId(Long userId) {
        return panierRepository.findAllByUserId(userId);
    }


    public double applyReduction(Long panierId,double percentReduction){
    Panier panier=panierRepository.findById(panierId).orElseThrow(()->new RuntimeException("the id of panier is not found"));
      double total=panier.getTotalPrice();
        double discountAmount = total * (percentReduction / 100);
        double totalAvecReduction = total - discountAmount;
        panier.setTotalPrice(totalAvecReduction);
        panierRepository.save(panier);

        return totalAvecReduction;
    }

    public double totalPriceCalculationWithDelivery(Long panierId)
    {
        Optional<Panier> optionalPanier=panierRepository.findById(panierId);
        if (optionalPanier.isPresent()) {
            Panier panier = optionalPanier.get();
            double totalPriceAvecLivraison = panier.getTotalPriceAvecLivraison();
            logger.info("Total price with delivery for Panier ID " + panierId + " is " + totalPriceAvecLivraison);
            return totalPriceAvecLivraison;
        } else {
            logger.warning("Panier ID " + panierId + " not found.");
            return 0.0;
        }
    }
    @Transactional
    public Panier confirmerPanier(Long panierId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        if (panier.getStatus() == PanierStatus.EN_COURS) {
            panier.setStatus(PanierStatus.CONFIRME); // Mise à jour du statut du panier
            panierRepository.save(panier);
            return panier;
        } else {
            throw new IllegalStateException("Le panier ne peut pas être confirmé car il n'est pas en cours");
        }
    }



    /*public Panier getPanierByIdN(Long panierId)
    {

    }*/

}