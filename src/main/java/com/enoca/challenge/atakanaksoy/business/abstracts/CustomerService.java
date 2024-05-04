package com.enoca.challenge.atakanaksoy.business.abstracts;

import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.CreateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.UpdateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.CreatedCustomerResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetAllCustomersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetCustomerByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.UpdatedCustomerResponse;

import java.util.List;

public interface CustomerService {
    CreatedCustomerResponse add(CreateCustomerRequest createCustomerRequest);
    UpdatedCustomerResponse update(UpdateCustomerRequest updateCustomerRequest);
    void delete(int id);
    GetCustomerByIdResponse getById(int id);
    List<GetAllCustomersResponse> getAll();
}
