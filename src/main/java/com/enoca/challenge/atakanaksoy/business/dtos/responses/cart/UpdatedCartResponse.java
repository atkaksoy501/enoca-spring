package com.enoca.challenge.atakanaksoy.business.dtos.responses.cart;

import com.enoca.challenge.atakanaksoy.entities.concretes.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedCartResponse {
    private int id;
    private int customerId;
    private double totalPrice;
    private List<Integer> productIds;
    private LocalDateTime updateDate;
}
