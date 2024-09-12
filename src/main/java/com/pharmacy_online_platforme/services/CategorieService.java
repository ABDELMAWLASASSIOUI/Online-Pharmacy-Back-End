package com.pharmacy_online_platforme.services;

import com.pharmacy_online_platforme.dto.CategorieDTO;

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
    public CategorieDTO createCategorie(CategorieDTO categoryDTO){
        System.out.println("Received CategoryDTO: " + categoryDTO);
        Categorie categorie=new Categorie();
        categorie.setName(categoryDTO.getName());
        categorie = categorieRepository.save(categorie);
        return convertDTO(categorie);

    }

    private CategorieDTO convertDTO(Categorie categorie)
    {
        CategorieDTO dto = new CategorieDTO();
        dto.setName(categorie.getName());
        return dto;

    }


    public CategorieDTO updateCategorie(Long id, CategorieDTO categorieDTO) {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);
        if (existingCategorie.isPresent()) {
            Categorie updatedCategorie = existingCategorie.get();
            updatedCategorie.setName(categorieDTO.getName());
          categorieRepository.save(updatedCategorie);
          return convertDTO(updatedCategorie);
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
