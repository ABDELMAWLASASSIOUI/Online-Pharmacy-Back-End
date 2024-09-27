package com.pharmacy_online_platforme.controllers;



import com.pharmacy_online_platforme.dto.LivraisonDTO;
import com.pharmacy_online_platforme.services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @PostMapping("/user/create")
    public ResponseEntity<LivraisonDTO> createLivraison(@RequestParam Long panierId,
                                                        @RequestParam String deliveryMethod) {
        LivraisonDTO livraisonDTO = livraisonService.createLivraison(panierId, deliveryMethod);
        return ResponseEntity.ok(livraisonDTO);
    }

    @GetMapping("/user/{livraisonId}")
    public ResponseEntity<LivraisonDTO> getLivraisonById(@PathVariable Long livraisonId) {
        LivraisonDTO livraisonDTO = livraisonService.getLivraisonById(livraisonId);
        return ResponseEntity.ok(livraisonDTO);
    }


    // Autres endpoints peuvent être ajoutés pour récupérer, mettre à jour ou supprimer des livraisons.
}
