package com.pharmacy_online_platforme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private double price;
    private Long categoryId;
    private Long imageId;  // Add this field to reference the image
   // private String imageData;  // Image data as Base64 string

}
