package com.enoca.challenge.atakanaksoy.business.dtos.requests.cart;

import com.enoca.challenge.atakanaksoy.entities.concretes.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCartRequest {
    private int customerId;
    private double totalPrice;
}
