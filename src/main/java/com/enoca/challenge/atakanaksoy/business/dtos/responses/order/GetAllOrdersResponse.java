package com.enoca.challenge.atakanaksoy.business.dtos.responses.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllOrdersResponse {
    private int id;
    private int customerId;
    private double totalPrice;
    private List<Integer> productIds;
}
