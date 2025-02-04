package com.example.e_commerce.controllers;

import com.example.e_commerce.dtos.ProductRequestDTO;
import com.example.e_commerce.dtos.ProductResponseDTO;
import com.example.e_commerce.exceptions.DuplicateProductException;
import com.example.e_commerce.exceptions.ProductNotFoundException;
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
        try {
            ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
        } catch (DuplicateProductException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> allProducts() {
        List<ProductResponseDTO> products = productService.allProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}