package com.example.e_commerce.services;

import com.example.e_commerce.dtos.CustomerRequestDTO;
import com.example.e_commerce.dtos.CustomerResponseDTO;
import com.example.e_commerce.exceptions.CustomerNotFoundException;
import com.example.e_commerce.exceptions.DuplicateCustomerException;
import com.example.e_commerce.models.Customer;
import com.example.e_commerce.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDTO createClient(CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> existingClient = customerRepository.findByCpf(customerRequestDTO.getCpf());
        if (existingClient.isPresent()) {
            throw new DuplicateCustomerException("Customer with CPF: " + customerRequestDTO.getCpf() + " already exists.");
        }

        Customer customer = new Customer(
                customerRequestDTO.getName(),
                customerRequestDTO.getCpf(),
                customerRequestDTO.getEmail()
        );

        Customer customerSave = customerRepository.save(customer);
        return new CustomerResponseDTO(
                customerSave.getId(),
                customerSave.getName(),
                customerSave.getCpf(),
                customerSave.getEmail()
        );
    }

    public List<CustomerResponseDTO> allClients() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerResponseDTO(customer.getId(), customer.getName(), customer.getCpf(), customer.getEmail()))
                .toList();
    }

    public CustomerResponseDTO getClientByCpf(String cpf) {
        Customer customer = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with CPF: " + cpf + " not found."));

        return new CustomerResponseDTO(customer.getId(), customer.getName(), customer.getCpf(), customer.getEmail());
    }

    private CustomerResponseDTO mapToResponse(Customer customer) {
        return new CustomerResponseDTO(customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getEmail());
    }

    public CustomerResponseDTO updateClient(String cpf, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with CPF: " + cpf + " not found."));

        customer.setName(customerRequestDTO.getName());
        customer.setCpf(customerRequestDTO.getCpf());
        customer.setEmail(customerRequestDTO.getEmail());

        return mapToResponse(customerRepository.save(customer));
    }
}