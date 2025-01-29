package com.example.e_commerce.services;

import com.example.e_commerce.dtos.ClientRequestDTO;
import com.example.e_commerce.dtos.ClientResponseDTO;
import com.example.e_commerce.models.Client;
import com.example.e_commerce.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        Client client = new Client(
                clientRequestDTO.getName(),
                clientRequestDTO.getCpf(),
                clientRequestDTO.getEmail()
        );
        Client clientSave = clientRepository.save(client);
        return new ClientResponseDTO(
                clientSave.getId(),
                clientSave.getName(),
                clientSave.getCpf(),
                clientSave.getEmail()
        );
    }
}
