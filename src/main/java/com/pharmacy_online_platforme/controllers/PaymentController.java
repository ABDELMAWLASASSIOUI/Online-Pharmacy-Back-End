package com.pharmacy_online_platforme.controllers;


import com.pharmacy_online_platforme.dto.PaymentInfoDTO;
import com.pharmacy_online_platforme.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PanierService panierService;


}
