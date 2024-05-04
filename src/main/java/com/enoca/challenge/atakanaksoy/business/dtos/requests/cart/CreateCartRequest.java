package com.enoca.challenge.atakanaksoy.business.dtos.requests.cart;

import com.enoca.challenge.atakanaksoy.entities.concretes.CartProduct;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCartRequest {
    @NotNull
    private int customerId;

    @NotNull
    private List<CartProduct> products;

    private double totalPrice;
}
