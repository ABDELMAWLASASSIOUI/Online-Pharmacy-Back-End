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
    public Categorie updateCategorie(Long id,Categorie categorie) //not work
    {
        if(categorieRepository.existsById(id)){
            categorie.setId(id);
            return categorieRepository.save(categorie);
        }
        return null;
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
