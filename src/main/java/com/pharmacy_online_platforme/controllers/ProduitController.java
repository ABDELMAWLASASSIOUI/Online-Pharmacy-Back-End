package com.pharmacy_online_platforme.controllers;

import com.pharmacy_online_platforme.dto.ProductDTO;
import com.pharmacy_online_platforme.entites.Produit;
import com.pharmacy_online_platforme.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProduitController {

 @Autowired
 private ProduitService produitService;

    @PostMapping("/admin/add/product")
    public ResponseEntity<ProductDTO> createProduit(@RequestBody ProductDTO produit) {
        ProductDTO createdProduit = produitService.createProduit(produit);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduit);
    }


    @GetMapping("/admin/getAll/products")//is work
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }
    @GetMapping("/admin/getById/product/{id}") //is Work
    public Optional<Produit> getProduitById(@PathVariable Long id){
        return produitService.getProduitById(id);
    }
    @DeleteMapping("/admin/deleteById/product/{id}")//is work
    public ResponseEntity<String> deleteProduitById(@PathVariable Long id) {
        if (!produitService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produit avec l'ID " + id + " n'existe pas.");
        }

        produitService.deleteProduit(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Produit supprimé avec succès : ID " + id);
    }
    @PutMapping("/admin/updateById/product/{id}")//is work
    public ResponseEntity<ProductDTO> updateProduit(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO productDTO1=produitService.updateProduct(id, productDTO);
        return ResponseEntity.ok(productDTO1);



    }


    }
