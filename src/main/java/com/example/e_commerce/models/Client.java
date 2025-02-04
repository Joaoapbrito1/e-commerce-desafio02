package com.example.e_commerce.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome do cliente é obrigatório.")
    private String name;

    //@CPF(message = "O cpf deve ser válido.")
    @Column(unique = true, nullable = false)
    private String cpf;

    @Email(message = "O email deve ser válido")
    @Column(unique = true, nullable = false)
    private String email;

    public Client() {
    }

    public Client(String name, String cpf, String email){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
}