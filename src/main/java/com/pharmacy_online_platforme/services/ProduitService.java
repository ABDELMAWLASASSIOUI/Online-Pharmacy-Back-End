package com.pharmacy_online_platforme.services;

import com.pharmacy_online_platforme.entites.Produit;
import com.pharmacy_online_platforme.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public Produit createProduit(Produit produit) {return produitRepository.save(produit);}
    public List<Produit>  getAllProduits(){ return produitRepository.findAll();}
    public Optional<Produit> getProduitById(Long id) {return produitRepository.findById(id);}
    public boolean existsById(Long id) {
        return produitRepository.existsById(id);
    }

    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    public void deleteProduit(Long id) {produitRepository.deleteById(id);}


}
