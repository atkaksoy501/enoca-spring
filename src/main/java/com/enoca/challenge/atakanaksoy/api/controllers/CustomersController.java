package com.enoca.challenge.atakanaksoy.api.controllers;

import com.enoca.challenge.atakanaksoy.business.abstracts.CustomerService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.CreateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.UpdateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.CreatedCustomerResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetAllCustomersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetCustomerByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.UpdatedCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomersController {
    private final CustomerService customerService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCustomerResponse add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.add(createCustomerRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCustomerResponse update(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        customerService.delete(id);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerByIdResponse getById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCustomersResponse> getAll() {
        return customerService.getAll();
    }
}
