package com.pharmacy_online_platforme.dto;


import lombok.Data;
import java.util.List;

@Data
public class LivraisonDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String address;
    private String phoneNumber;  // Ajout du numéro de téléphone
    private String deliveryMethod;
    private String status;
    private String deliveryDate;
    private List<ProductDTO> products;
    private Double totalPrice;
}


