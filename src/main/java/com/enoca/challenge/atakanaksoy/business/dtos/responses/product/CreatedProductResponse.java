package com.enoca.challenge.atakanaksoy.business.dtos.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedProductResponse {
    private int id;
    private String name;
    private double price;
    private int stock;
    private LocalDateTime createDate;
}
