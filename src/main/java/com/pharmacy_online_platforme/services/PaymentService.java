package com.pharmacy_online_platforme.services;

import com.pharmacy_online_platforme.dto.PaymentDetailsDTO;
import com.pharmacy_online_platforme.entites.Panier;
import com.pharmacy_online_platforme.repositories.PanierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PanierRepository panierRepository;
    @Transactional
    public void processPayment(PaymentDetailsDTO paymentDetails) {
        Panier panier = panierRepository.findById(paymentDetails.getPanierId())
                .orElseThrow(()-> new RuntimeException("Panier not found"));
        double totalAmount = panier.getTotalPrice();
        // implement of type the payments
        switch (paymentDetails.getPaymentMethod()) {
            case PAYPAL:
                // Logic for PayPal payment
                // String paypalEmail = paymentDetails.getPaypalEmail(); // Removed
                break;
            case CREDIT_CARD:
                // Logic for credit card payment
                String creditCardNumber = paymentDetails.getCreditCardNumber();
                String securityCode = paymentDetails.getSecurityCode();
                String expirationDate = paymentDetails.getExpirationDate();
                // Validate and process credit card payment
                break;
            case DEBIT_CARD:
                // Logic for debit card payment
                // Similar to credit card processing
                break;
            case BANK_TRANSFER:
                // Logic for bank transfer payment
                break;
            case CASH:
                // Logic for cash payment
                break;
            default:
                throw new RuntimeException("Unsupported payment method");
        }
        panier.getItems().clear(); // Clear the panier items after payment
        panierRepository.save(panier);

    }
    public PaymentDetailsDTO getPaymentDetails(Long panierId) { //is not working
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier not found"));

        // Assuming the payment details are stored in the Panier entity
        PaymentDetailsDTO paymentDetails = new PaymentDetailsDTO();
        paymentDetails.setPanierId(panier.getId());
        paymentDetails.setPaymentMethod(paymentDetails.getPaymentMethod());
        paymentDetails.setCreditCardNumber(paymentDetails.getCreditCardNumber());
        paymentDetails.setSecurityCode(paymentDetails.getSecurityCode());
        paymentDetails.setExpirationDate(paymentDetails.getExpirationDate());

        return paymentDetails;
    }

}
