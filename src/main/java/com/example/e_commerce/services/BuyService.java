package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.InsufficientStockPileException;
import com.example.e_commerce.exceptions.BuyNotFoundException;
import com.example.e_commerce.exceptions.InvalidBuyException;
import com.example.e_commerce.models.Merchandise;
import com.example.e_commerce.repositories.MerchandiseRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyService {
    private final MerchandiseRepository merchandiseRepository;

    public BuyService(MerchandiseRepository merchandiseRepository) {
        this.merchandiseRepository = merchandiseRepository;
    }

    public void purchaseProduct(Long productId) {
        if (productId == null || productId <= 0) {
            throw new InvalidBuyException("Invalid merchandise ID");
        }

        Merchandise merchandise = merchandiseRepository.findById(productId)
                .orElseThrow(() -> new BuyNotFoundException("Merchandise not found"));

        if (merchandise.getQuantity() <= 0) {
            throw new InsufficientStockPileException("Merchandise out of stock");
        }

        merchandise.setQuantity(merchandise.getQuantity() - 1);
        merchandiseRepository.save(merchandise);
    }
}