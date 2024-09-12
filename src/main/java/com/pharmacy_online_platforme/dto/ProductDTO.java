package com.pharmacy_online_platforme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private double price;
    private Long categoryId;

}
