package com.example.e_commerce.controllers;

import com.example.e_commerce.services.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<String> purchaseProduct(@PathVariable Long productId) {
        purchaseService.purchaseProduct(productId);
        return ResponseEntity.ok("Purchase successful");
    }
}