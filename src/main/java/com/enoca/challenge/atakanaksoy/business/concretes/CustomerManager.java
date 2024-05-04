package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.CustomerService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.CreateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.UpdateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.CreatedCustomerResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetAllCustomersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetCustomerByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.UpdatedCustomerResponse;
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
    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest createCustomerRequest) {
        Customer customer = modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        customer.setCreateDate(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapperService.forResponse().map(savedCustomer, CreatedCustomerResponse.class);
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
