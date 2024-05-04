package com.enoca.challenge.atakanaksoy.business.dtos.requests.product;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductRequest {
    @Size(min = 2)
    private String name;

    private double price;

    private int stock;
}
