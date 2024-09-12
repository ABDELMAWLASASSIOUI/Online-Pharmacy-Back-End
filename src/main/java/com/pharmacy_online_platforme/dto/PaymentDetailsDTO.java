package com.pharmacy_online_platforme.dto;

import com.pharmacy_online_platforme.entites.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class PaymentDetailsDTO {
    private Long panierId;
    private PaymentMethod paymentMethod;
    private String creditCardNumber;
    private String securityCode;
    private String expirationDate; // Add expiration date field


}
