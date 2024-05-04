package com.enoca.challenge.atakanaksoy.business.dtos.requests.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProductRequest {
    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    private double price;

    @NotNull
    private int stock;
}
