package com.example.e_commerce.controllers;

import com.example.e_commerce.dtos.ProductRequestDTO;
import com.example.e_commerce.dtos.ProductResponseDTO;
import com.example.e_commerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> allProducts() {
        List<ProductResponseDTO> produts = productService.allProducts();
        return ResponseEntity.ok(produts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@Valid @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}