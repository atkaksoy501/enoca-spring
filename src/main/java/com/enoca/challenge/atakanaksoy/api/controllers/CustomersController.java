package com.enoca.challenge.atakanaksoy.api.controllers;

import com.enoca.challenge.atakanaksoy.business.abstracts.CustomerService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.CreateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.customer.UpdateCustomerRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.CreatedCustomerResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetAllCustomersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.GetCustomerByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.customer.UpdatedCustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer Management", description = "Customer Management APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomersController {
    private final CustomerService customerService;

    @Operation(summary = "Add new Customer")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCustomerResponse add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.add(createCustomerRequest);
    }

    @Operation(summary = "Update an existing Customer with id")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCustomerResponse update(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest, @PathVariable int id) {
        return customerService.update(updateCustomerRequest, id);
    }

    @Operation(summary = "Soft delete an existing Customer with id")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        customerService.delete(id);
    }

    @Operation(summary = "Get an existing Customer with id")
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerByIdResponse getById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @Operation(summary = "Get all Customers")
    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCustomersResponse> getAll() {
        return customerService.getAll();
    }
}
