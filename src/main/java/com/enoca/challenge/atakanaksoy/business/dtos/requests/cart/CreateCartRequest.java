package com.enoca.challenge.atakanaksoy.business.dtos.requests.cart;

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
}
