package com.enoca.challenge.atakanaksoy.business.dtos.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetProductByIdResponse {
    private int id;
    private String name;
    private double price;
    private int stock;
}
