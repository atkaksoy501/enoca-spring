package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.CustomerService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.CreateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.UpdateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.CreatedCustomerResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetAllCustomersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetCustomerByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.UpdatedCustomerResponse;
import com.enoca.challenge.atakanaksoy.business.rules.CustomerBusinessRules;
import com.enoca.challenge.atakanaksoy.core.utilities.mapping.ModelMapperService;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.CustomerRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerBusinessRules customerBusinessRules;
    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest createCustomerRequest) {
        Customer customer = modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        customer.setActive(true);
        customer.setCreateDate(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapperService.forResponse().map(savedCustomer, CreatedCustomerResponse.class);
    }

    @Override
    public UpdatedCustomerResponse update(UpdateCustomerRequest updateCustomerRequest, int id) {
        Customer customer = customerBusinessRules.customerMustExists(id);
        modelMapperService.forUpdate().map(updateCustomerRequest, customer);
        customer.setUpdateDate(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapperService.forResponse().map(savedCustomer, UpdatedCustomerResponse.class);
    }

    @Override
    public void delete(int id) {
        Customer customer = customerBusinessRules.customerMustExists(id);
        customer.setActive(false);
        customer.setDeleteDate(LocalDateTime.now());
        customerRepository.save(customer);
    }

    @Override
    public GetCustomerByIdResponse getById(int id) {
        Customer customer = customerBusinessRules.customerMustExists(id);
        return modelMapperService.forResponse().map(customer, GetCustomerByIdResponse.class);
    }

    @Override
    public List<GetAllCustomersResponse> getAll() {
        List<Customer> customers = customerRepository.findAllByActiveTrue();
        return customers.stream().map(
                customer -> modelMapperService.forResponse().map(customer, GetAllCustomersResponse.class)
        ).toList();
    }
}
