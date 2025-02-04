package com.example.e_commerce.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class MerchandiseRequestDTO {

    @NotBlank(message = "the product name is mandatory")
    @Column(unique = true, nullable = false)
    private String name;

    @Positive(message = "The price must be greater than zero")
    private double price;

    @Min(value = 0, message = "The quantity must be greater than or equal to zero")
    private int quantity;


    public MerchandiseRequestDTO(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}