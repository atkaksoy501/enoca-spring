package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.CustomerService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.CreateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.UpdateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.CreatedCustomerResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetAllCustomersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetCustomerByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.UpdatedCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest createCustomerRequest) {
        return null;
    }

    @Override
    public UpdatedCustomerResponse update(UpdateCustomerRequest updateCustomerRequest, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public GetCustomerByIdResponse getById(int id) {
        return null;
    }

    @Override
    public List<GetAllCustomersResponse> getAll() {
        return null;
    }
}
