package com.enoca.challenge.atakanaksoy.business.dtos.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedCustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createDate;
}
