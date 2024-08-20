package com.pharmacy_online_platforme.controllers;

import com.pharmacy_online_platforme.entites.Panier;
import com.pharmacy_online_platforme.entites.PanierAddResquest;
import com.pharmacy_online_platforme.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class PanierController {
    @Autowired
    private PanierService panierService;


    @PostMapping("/user/add/item")
    public ResponseEntity<String> addItemToPanier(@RequestBody PanierAddResquest request) {
        Panier panier = panierService.addItemToPanier(request.getPanierId(), request.getProduitId(), request.getQuantity());
        if (panier != null) {
            return ResponseEntity.ok("L'article a été ajouté au panier avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le panier ou le produit n'a pas été trouvé.");
        }
    }
    @PostMapping("/user/add/panier")
    public ResponseEntity<String> createPanier(@RequestParam Long userId) {
        try {
            Panier panier = panierService.createPanier(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Panier créé avec succès");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    /*
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier) {
        Panier createdPanier = panierService.createPanier(panier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPanier);
    }

     */


    @DeleteMapping("/user/delete/item/{itemId}")
    public ResponseEntity<String> removeItemFromPanier(@PathVariable Long itemId) {
        try {
            Panier panier = panierService.removeItemById(itemId);
            return ResponseEntity.ok("L'article a été supprimé du panier avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    private static final Logger logger = Logger.getLogger(PanierController.class.getName());

    @GetMapping("/user/get/totalPrice/{id}")
    public ResponseEntity<Double> getTotalPrice(@PathVariable Long id) {
        logger.info("Calculating total price for Panier ID " + id);
        double totalPrice = panierService.calculerTotalPrixPanier(id);
        logger.info("Total price for Panier ID " + id + " is " + totalPrice);
        return ResponseEntity.ok(totalPrice);
    }

}
