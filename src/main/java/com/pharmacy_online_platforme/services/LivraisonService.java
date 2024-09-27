package com.pharmacy_online_platforme.services;


import com.pharmacy_online_platforme.dto.LivraisonDTO;

import com.pharmacy_online_platforme.dto.ProductDTO;
import com.pharmacy_online_platforme.entites.Livraison;
import com.pharmacy_online_platforme.entites.Panier;
import com.pharmacy_online_platforme.entites.User;
import com.pharmacy_online_platforme.repositories.AuthRepo;
import com.pharmacy_online_platforme.repositories.LivraisonRepository;
import com.pharmacy_online_platforme.repositories.PanierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Autowired
    private AuthRepo userRepository;

    @Autowired
    private PanierRepository panierRepository;

    public LivraisonDTO createLivraison(Long panierId, String deliveryMethod) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));

        User user = panier.getUser();  // Récupérer l'utilisateur associé au panier

        Livraison livraison = new Livraison();
        livraison.setUser(user);
        livraison.setPanier(panier);
        livraison.setAddress(user.getAddress());  // Récupérer l'adresse de l'utilisateur
        livraison.setName(user.getName());  // Récupérer le nom de l'utilisateur
        livraison.setPhoneNumber(user.getNumbertelphone());  // Récupérer le numéro de téléphone
        livraison.setDeliveryMethod(deliveryMethod);
        livraison.setStatus("En cours");
        livraison.setDeliveryDate(new Date());

        livraisonRepository.save(livraison);

        // Convertir l'entité Livraison en DTO et renvoyer
        return convertToDTO(livraison);
    }

    public LivraisonDTO convertToDTO(Livraison livraison) {
        LivraisonDTO livraisonDTO = new LivraisonDTO();
        livraisonDTO.setId(livraison.getId());
        livraisonDTO.setUserId(livraison.getUser().getId().longValue());
        livraisonDTO.setUserName(livraison.getUser().getName());
        livraisonDTO.setAddress(livraison.getAddress());
        livraisonDTO.setPhoneNumber(livraison.getPhoneNumber());  // Ajouter le numéro de téléphone
        livraisonDTO.setDeliveryMethod(livraison.getDeliveryMethod());
        livraisonDTO.setStatus(livraison.getStatus());
        livraisonDTO.setDeliveryDate(livraison.getDeliveryDate().toString());

        livraisonDTO.setProducts(livraison.getPanier().getItems().stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(product.getProduit().getName());
            productDTO.setPrice(product.getProduit().getPrice());

            return productDTO;
        }).collect(Collectors.toList()));

        livraisonDTO.setTotalPrice(livraison.getPanier().getTotalPrice());

        return livraisonDTO;
    }


    public LivraisonDTO getLivraisonById(Long livraisonId) {
        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(() -> new RuntimeException("Livraison non trouvée"));
        return convertToDTO(livraison);  // Convertir l'entité Livraison en DTO
    }
}
