package com.enoca.challenge.atakanaksoy.business.dtos.requests.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCartRequest {
    private int customerId;
    private double totalPrice;
}
