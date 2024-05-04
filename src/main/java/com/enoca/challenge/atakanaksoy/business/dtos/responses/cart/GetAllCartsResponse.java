package com.enoca.challenge.atakanaksoy.business.dtos.responses.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCartsResponse {
    private int id;
    private int customerId;
    private double totalPrice;
    private List<Integer> productIds;
}
