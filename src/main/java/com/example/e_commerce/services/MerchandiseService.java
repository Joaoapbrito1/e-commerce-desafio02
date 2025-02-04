package com.example.e_commerce.services;

import com.example.e_commerce.dtos.MerchandiseRequestDTO;
import com.example.e_commerce.dtos.MerchandiseResponseDTO;
import com.example.e_commerce.exceptions.DuplicateMerchandiseException;
import com.example.e_commerce.exceptions.MerchandiseNotFoundException;
import com.example.e_commerce.models.Merchandise;
import com.example.e_commerce.repositories.MerchandiseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchandiseService {
    private final MerchandiseRepository merchandiseRepository;

    public MerchandiseService(MerchandiseRepository merchandiseRepository) {
        this.merchandiseRepository = merchandiseRepository;
    }

    public MerchandiseResponseDTO createProduct(MerchandiseRequestDTO merchandiseRequestDTO) {
        if (merchandiseRepository.findByName(merchandiseRequestDTO.getName()).isPresent()) {
            throw new DuplicateMerchandiseException("Merchandise with this name already exists.");
        }
        Merchandise merchandise = new Merchandise(
                merchandiseRequestDTO.getName(),
                merchandiseRequestDTO.getPrice(),
                merchandiseRequestDTO.getQuantity()
        );
        Merchandise merchandiseSave = merchandiseRepository.save(merchandise);
        return new MerchandiseResponseDTO(
                merchandiseSave.getId(),
                merchandiseSave.getName(),
                merchandiseSave.getPrice(),
                merchandiseSave.getQuantity()
        );
    }

    public List<MerchandiseResponseDTO> allProducts() {
        return merchandiseRepository.findAll().stream()
                .map(merchandise -> new MerchandiseResponseDTO(merchandise.getId(), merchandise.getName(), merchandise.getPrice(), merchandise.getQuantity()))
                .toList();
    }

    public void deleteProduct(Long id) {
        if (!merchandiseRepository.existsById(id)) {
            throw new MerchandiseNotFoundException("Merchandise not found for deletion.");
        }
        merchandiseRepository.deleteById(id);
    }
}