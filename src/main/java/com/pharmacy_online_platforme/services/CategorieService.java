package com.pharmacy_online_platforme.services;

import com.pharmacy_online_platforme.entites.Categorie;
import com.pharmacy_online_platforme.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;
    public Categorie createCategorie(Categorie categorie){
        return categorieRepository.save(categorie);
    }
    public Categorie updateCategorie(Long id, Categorie categorie) {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);
        if (existingCategorie.isPresent()) {
            Categorie updatedCategorie = existingCategorie.get();
            updatedCategorie.setName(categorie.getName());
            return categorieRepository.save(updatedCategorie);
        } else {
            throw new RuntimeException("Categorie not found with id: " + id);
        }
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
    public Optional<Categorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }
}
