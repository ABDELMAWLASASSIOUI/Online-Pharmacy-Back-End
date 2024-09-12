package com.pharmacy_online_platforme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PanierItem {
    private Long productId;
    private Long panierId;
    private int quantity;
}
