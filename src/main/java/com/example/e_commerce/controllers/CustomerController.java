package com.example.e_commerce.controllers;


import com.example.e_commerce.dtos.CustomerRequestDTO;
import com.example.e_commerce.dtos.CustomerResponseDTO;
import com.example.e_commerce.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createClient(@Valid @RequestBody CustomerRequestDTO clienteRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.createClient(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> allClients() {
        List<CustomerResponseDTO> clients = customerService.allClients();
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDTO> getClientByCpf(@PathVariable String cpf) {
        CustomerResponseDTO client = customerService.getClientByCpf(cpf);
        return ResponseEntity.ok(client);
    }
    @PutMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDTO> updateClient(@PathVariable String cpf, @Valid @RequestBody CustomerRequestDTO customerRequestDTO){
        return ResponseEntity.ok(customerService.updateClient(cpf, customerRequestDTO));
    }
}
