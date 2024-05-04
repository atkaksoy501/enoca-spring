package com.enoca.challenge.atakanaksoy.business.rules;

import com.enoca.challenge.atakanaksoy.business.messages.CustomerMessages;
import com.enoca.challenge.atakanaksoy.core.utilities.exceptions.types.BusinessException;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.CustomerRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;

    public Customer customerMustExists(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null || !customer.isActive()) {
            throw new BusinessException(CustomerMessages.CUSTOMER_NOT_EXISTS);
        }
        else return customer;
    }
}
