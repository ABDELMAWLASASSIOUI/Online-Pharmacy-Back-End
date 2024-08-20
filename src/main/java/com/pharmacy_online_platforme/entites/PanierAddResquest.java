package com.pharmacy_online_platforme.entites;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PanierAddResquest {

    private Long panierId;
    private Long produitId;
    private int quantity;
}