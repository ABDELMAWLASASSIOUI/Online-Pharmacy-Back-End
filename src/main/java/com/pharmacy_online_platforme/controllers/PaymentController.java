package com.pharmacy_online_platforme.controllers;

import com.pharmacy_online_platforme.dto.PaymentDetailsDTO;
import com.pharmacy_online_platforme.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/user/payment")
    public String processPayment(@RequestBody PaymentDetailsDTO paymentDetails) {
        paymentService.processPayment(paymentDetails);
        return "Payment successful for panier ID: " + paymentDetails.getPanierId() +
                " using " + paymentDetails.getPaymentMethod();
    }
    @GetMapping("/user/payment/details/{panierId}") //is not working
    public PaymentDetailsDTO getPaymentDetails(@PathVariable Long panierId) {
        return paymentService.getPaymentDetails(panierId);
    }

}
