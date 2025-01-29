package com.example.e_commerce.controllers;


import com.example.e_commerce.dtos.ClientRequestDTO;
import com.example.e_commerce.dtos.ClientResponseDTO;
import com.example.e_commerce.models.Client;
import com.example.e_commerce.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO clienteRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.createClient(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponseDTO);
    }
}
