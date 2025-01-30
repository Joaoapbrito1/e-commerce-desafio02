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
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> allClients() {
        List<ClientResponseDTO> clients = clientService.allClients();
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<ClientResponseDTO> getClientByCpf(@PathVariable String cpf) {
        ClientResponseDTO client = clientService.getClientByCpf(cpf);
        return ResponseEntity.ok(client);
    }
    @PutMapping("/{cpf}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable String cpf, @Valid @RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok(clientService.updateClient(cpf, clientRequestDTO));
    }
}
