package com.pharmacy_online_platforme.controllers;

import com.pharmacy_online_platforme.dto.CategorieDTO;

import com.pharmacy_online_platforme.entites.Categorie;
import com.pharmacy_online_platforme.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;
    @PostMapping("/admin/add/categories") //is work
    public ResponseEntity<CategorieDTO> createCategorie(@RequestBody CategorieDTO categorie) {
        CategorieDTO createdCategorie = categorieService.createCategorie(categorie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategorie);
    }
    @GetMapping("/admin/getById/categories/{id}")//is work
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id){
        Optional<Categorie> categorie=categorieService.getCategorieById(id);
        return categorie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping("/admin/getAllCategories")
    public List<Categorie> getAllCatories(){
        return categorieService.getAllCategories();
    }

    @PutMapping("/admin/updateById/categories/{id}")//is work
    public ResponseEntity<String> updateCategorie(@PathVariable Long id, @RequestBody CategorieDTO categorieDTO) {
        try {
            CategorieDTO updatedCategorie = categorieService.updateCategorie(id, categorieDTO);
            return ResponseEntity.ok("La catégorie a été mise à jour avec succès : " + updatedCategorie.getName());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur : La catégorie avec l'identifiant " + id + " n'a pas été trouvée.");
        }
    }

    @DeleteMapping("/admin/deleteById/categories/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        String message = "La catégorie avec l'ID " + id + " a été supprimée avec succès.";
        return new ResponseEntity<>(message,HttpStatus.OK); //noContent().build();
    }

    @GetMapping("/admin/hellofinaml")
    public String testing(){
        return "hello world final";
    }

    @GetMapping("/admin/hellofinamljj")
    public String testingj(){
        return "hello world fina";
    }



    @GetMapping("/admin/update")
    public String testingJenkinsfinal(){
        return "juste update the name of image docker  ";
    }



}
