package com.example.e_commerce.controllers;

import com.example.e_commerce.dtos.MerchandiseRequestDTO;
import com.example.e_commerce.dtos.MerchandiseResponseDTO;
import com.example.e_commerce.exceptions.DuplicateMerchandiseException;
import com.example.e_commerce.exceptions.MerchandiseNotFoundException;
import com.example.e_commerce.services.MerchandiseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @PostMapping
    public ResponseEntity<MerchandiseResponseDTO> createProduct(@Valid @RequestBody MerchandiseRequestDTO merchandiseRequestDTO) {
        try {
            MerchandiseResponseDTO merchandiseResponseDTO = merchandiseService.createProduct(merchandiseRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(merchandiseResponseDTO);
        } catch (DuplicateMerchandiseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<MerchandiseResponseDTO>> allProducts() {
        List<MerchandiseResponseDTO> products = merchandiseService.allProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            merchandiseService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (MerchandiseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}