package com.example.e_commerce.services;

import com.example.e_commerce.dtos.ProductRequestDTO;
import com.example.e_commerce.dtos.ProductResponseDTO;
import com.example.e_commerce.exceptions.DuplicateProductException;
import com.example.e_commerce.exceptions.ProductNotFoundException;
import com.example.e_commerce.models.Product;
import com.example.e_commerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        if (productRepository.findByName(productRequestDTO.getName()).isPresent()) {
            throw new DuplicateProductException("Product with this name already exists.");
        }
        Product product = new Product(
                productRequestDTO.getName(),
                productRequestDTO.getPrice(),
                productRequestDTO.getQuantity()
        );
        Product productSave = productRepository.save(product);
        return new ProductResponseDTO(
                productSave.getId(),
                productSave.getName(),
                productSave.getPrice(),
                productSave.getQuantity()
        );
    }

    public List<ProductResponseDTO> allProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getQuantity()))
                .toList();
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found for deletion.");
        }
        productRepository.deleteById(id);
    }
}