package com.pharmacy_online_platforme.controllers;

import com.pharmacy_online_platforme.entites.Categorie;
import com.pharmacy_online_platforme.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;
    @PostMapping("/admin/add/categories") //is work
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie createdCategorie = categorieService.createCategorie(categorie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategorie);
    }
    @GetMapping("/admin/getById/categories/{id}")//is work
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id){
        Optional<Categorie> categorie=categorieService.getCategorieById(id);
        return categorie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
    @PutMapping("/admin/updateById/categories/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        Categorie updatedCategorie = categorieService.updateCategorie(id, categorie);
        return updatedCategorie != null ? ResponseEntity.ok(updatedCategorie) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/admin/deleteById/categories/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}
