package com.pharmacy_online_platforme.services;

import com.pharmacy_online_platforme.dto.ProductDTO;
import com.pharmacy_online_platforme.entites.Categorie;
import com.pharmacy_online_platforme.entites.Produit;
import com.pharmacy_online_platforme.repositories.CategorieRepository;
import com.pharmacy_online_platforme.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    public ProductDTO createProduit(ProductDTO productDTO) {
        Produit produit=new Produit();
        produit.setName(productDTO.getName());
        produit.setPrice(productDTO.getPrice());
        Categorie categorie=categorieRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new RuntimeException("Categorie not found"));
         produit.setCategorie(categorie);
        produitRepository.save(produit);
        return convertDTO(produit);
    }
    public ProductDTO convertDTO(Produit produit)
    {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setName(produit.getName());
        productDTO.setPrice(produit.getPrice());
        productDTO.setCategoryId(produit.getCategorie().getId());
        return productDTO;
    }

    public List<Produit>  getAllProduits(){ return produitRepository.findAll();}
    public Optional<Produit> getProduitById(Long id) {return produitRepository.findById(id);}
    public boolean existsById(Long id) {
        return produitRepository.existsById(id);
    }

    /*
    public ProductDTO updateProduit(Long id,ProductDTO productDTO) {
        Produit produit=new Produit();
        Produit existeIdProductUpdate=produitRepository.findById(id).orElseThrow(()->new RuntimeException("the produit is not existe"));
            produit.setName(productDTO.getName());
            produit.setPrice(productDTO.getPrice());
            Categorie categorie=categorieRepository.findById(productDTO.getCategoryId()).orElseThrow(()->new RuntimeException("Categorie not found"));
            produit.setCategorie(categorie);
            produitRepository.save(produit);
            return convertDTO(produit);

    }

     */
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        produit.setName(productDTO.getName());
        produit.setPrice(productDTO.getPrice());

        // Vérification et mise à jour de la catégorie si nécessaire
        if (productDTO.getCategoryId() != null) { //it nessary to verfier this id if not add the new product not update
            Categorie categorie = categorieRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categorie not found"));
            produit.setCategorie(categorie);
        }

        produit = produitRepository.save(produit);
        return convertDTO(produit);
    }
    public void deleteProduit(Long id) {produitRepository.deleteById(id);}


}
