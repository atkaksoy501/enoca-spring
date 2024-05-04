package com.enoca.challenge.atakanaksoy.business.dtos.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCustomerByIdResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
