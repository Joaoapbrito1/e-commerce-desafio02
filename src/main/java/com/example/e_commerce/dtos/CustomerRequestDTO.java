package com.example.e_commerce.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequestDTO {

    @NotBlank(message = "O nome do cliente é obrigatório.")
    private String name;

    //@CPF(message = "O cpf deve ser válido.")
    @Column(unique = true, nullable = false)
    private String cpf;

    @Email(message = "O email deve ser válido")
    @Column(unique = true, nullable = false)
    private String email;

    public CustomerRequestDTO(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}