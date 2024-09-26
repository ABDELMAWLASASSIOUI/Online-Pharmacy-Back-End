package com.pharmacy_online_platforme.controllers;

import com.pharmacy_online_platforme.dto.PanierDTO;
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

import java.util.List;
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
    public ResponseEntity<String> createPanier(@RequestBody PanierDTO panierDTO) {
        try {
            PanierDTO panierDTO1 = panierService.createPanierDTO(panierDTO);
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

    @GetMapping("/user/get/totalPriceOfOnePanier/{id}")
    public ResponseEntity<Double> getTotalPrice(@PathVariable Long id) {
        logger.info("Calculating total price for Panier ID " + id);
        double totalPrice = panierService.calculerTotalPrixPanier(id);
        logger.info("Total price for Panier ID " + id + " is " + totalPrice);
        return ResponseEntity.ok(totalPrice);
    }
   /* @GetMapping("/user/get/panier/{id}")
    public ResponseEntity<Panier> getPanier(@PathVariable Long id) {
        try {
            Panier panier = panierService.getPanierById(id);
            return ResponseEntity.ok(panier);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    */
   @GetMapping("/user/get/panier/{id}")
   public ResponseEntity<Panier> getPanier(@PathVariable Long id) {
       try {
           Panier panier = panierService.getPanierById(id);
           if (panier.getUser() == null) {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
           }
           return ResponseEntity.ok(panier);
       } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
   }
   //getPaniersByUserId
   @GetMapping("/user/getPaniersByUserId/{id}")
    public ResponseEntity<List<Panier>> getPaniersByUserId(@PathVariable Long id){
       List<Panier> paniers=panierService.getPaniersByUserId(id);
       return ResponseEntity.ok(paniers);
   }
    @PutMapping("/user/{panierId}/applyDiscount")
    public ResponseEntity<Double> appliquerReduction(@PathVariable Long panierId,
                                                     @RequestParam double pourcentageReduction) {
        double totalAvecReduction = panierService.applyReduction(panierId, pourcentageReduction);
        return ResponseEntity.ok(totalAvecReduction);
    }
    @GetMapping("/user/get/totalPriceWithDelivery/{id}")
    public ResponseEntity<Double> getTotalPriceWithDelivery(@PathVariable Long id) {
        logger.info("Calculating total price with delivery for Panier ID " + id);
        double totalPriceWithDelivery = panierService.totalPriceCalculationWithDelivery(id);
        return ResponseEntity.ok(totalPriceWithDelivery);
    }

    @PostMapping("/user/confirm/panier/{panierId}")
    public ResponseEntity<String> confirmerPanier(@PathVariable Long panierId) {
        try {
            panierService.confirmerPanier(panierId);
            return ResponseEntity.ok("Panier confirmé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
