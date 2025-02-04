package com.example.e_commerce.controllers;

import com.example.e_commerce.services.BuyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class BuyController {
    private final BuyService buyService;

    public BuyController(BuyService buyService) {
        this.buyService = buyService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<String> purchaseProduct(@PathVariable Long productId) {
        buyService.purchaseProduct(productId);
        return ResponseEntity.ok("Purchase successful");
    }
}