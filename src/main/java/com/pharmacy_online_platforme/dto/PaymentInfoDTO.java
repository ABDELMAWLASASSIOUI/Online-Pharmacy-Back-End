package com.pharmacy_online_platforme.dto;



import com.pharmacy_online_platforme.Enum.PaymentMethod;

public class PaymentInfoDTO {
    private Long userId;
   // private String userName; // Ajoutez le nom de l'utilisateur si nécessaire
    private PaymentMethod paymentMethod;
    private double totalAmount;



    private String userAddress;

    // Getters et Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }



    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
