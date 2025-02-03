//package com.example.e_commerce.services;
//
//import com.example.e_commerce.models.Product;
//import com.example.e_commerce.repositories.ProductRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//@Service
//public class PurchaseService {
//    private final ProductRepository productRepository;
//
//    public PurchaseService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    public void purchaseProduct(Long productId) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
//
//        if (product.getQuantity() <= 0) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product out of stock");
//        }
//
//        product.setQuantity(product.getQuantity() - 1);
//        productRepository.save(product);
//    }
//}
package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.InsufficientStockException;
import com.example.e_commerce.exceptions.PurchaseNotFoundException;
import com.example.e_commerce.exceptions.InvalidPurchaseException;
import com.example.e_commerce.models.Product;
import com.example.e_commerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final ProductRepository productRepository;

    public PurchaseService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void purchaseProduct(Long productId) {
        if (productId == null || productId <= 0) {
            throw new InvalidPurchaseException("Invalid product ID");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new PurchaseNotFoundException("Product not found"));

        if (product.getQuantity() <= 0) {
            throw new InsufficientStockException("Product out of stock");
        }

        product.setQuantity(product.getQuantity() - 1);
        productRepository.save(product);
    }
}