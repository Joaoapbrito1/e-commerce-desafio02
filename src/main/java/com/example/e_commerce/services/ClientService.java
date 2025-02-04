package com.example.e_commerce.services;

import com.example.e_commerce.dtos.ClientRequestDTO;
import com.example.e_commerce.dtos.ClientResponseDTO;
import com.example.e_commerce.exceptions.ClientNotFoundException;
import com.example.e_commerce.exceptions.DuplicateClientException;
import com.example.e_commerce.models.Client;
import com.example.e_commerce.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        Optional<Client> existingClient = clientRepository.findByCpf(clientRequestDTO.getCpf());
        if (existingClient.isPresent()) {
            throw new DuplicateClientException("Customer with CPF: " + clientRequestDTO.getCpf() + " already exists.");
        }

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

    public List<ClientResponseDTO> allClients() {
        return clientRepository.findAll().stream()
                .map(client -> new ClientResponseDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail()))
                .toList();
    }

    public ClientResponseDTO getClientByCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("Customer with CPF: " + cpf + " not found."));

        return new ClientResponseDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail());
    }

    private ClientResponseDTO mapToResponse(Client client) {
        return new ClientResponseDTO(client.getId(),
                client.getName(),
                client.getCpf(),
                client.getEmail());
    }

    public ClientResponseDTO updateClient(String cpf, ClientRequestDTO clientRequestDTO) {
        Client client = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("Customer with CPF: " + cpf + " not found."));

        client.setName(clientRequestDTO.getName());
        client.setCpf(clientRequestDTO.getCpf());
        client.setEmail(clientRequestDTO.getEmail());

        return mapToResponse(clientRepository.save(client));
    }
}