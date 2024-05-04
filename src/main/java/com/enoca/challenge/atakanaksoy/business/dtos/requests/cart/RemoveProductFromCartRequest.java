package com.enoca.challenge.atakanaksoy.business.dtos.requests.cart;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemoveProductFromCartRequest {
    @NotNull
    private int cartId;

    @NotNull
    private int productId;

    @NotNull
    private int quantity;
}
