package com.enoca.challenge.atakanaksoy.business.dtos.requests.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateOrderRequest {
    @NotNull
    private int customerId;

    @NotNull
    private List<Integer> productIds;

    private double totalPrice;

    private LocalDateTime date;
}
