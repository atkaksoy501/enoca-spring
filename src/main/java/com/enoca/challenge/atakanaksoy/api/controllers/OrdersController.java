package com.enoca.challenge.atakanaksoy.api.controllers;

import com.enoca.challenge.atakanaksoy.business.abstracts.OrderService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.CreateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.UpdateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.CreatedOrderResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetAllOrdersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetOrderByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.UpdatedOrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order Management", description = "Order Management APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;

    @Operation(summary = "Place a new Order")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedOrderResponse add(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.add(createOrderRequest);
    }

    @Operation(summary = "Update an existing Order with id")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedOrderResponse update(@Valid @RequestBody UpdateOrderRequest updateOrderRequest, @PathVariable int id) {
        return orderService.update(updateOrderRequest, id);
    }

    @Operation(summary = "Soft delete an existing Order with id")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        orderService.delete(id);
    }

    @Operation(summary = "Get an existing Order with id")
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetOrderByIdResponse getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @Operation(summary = "Get all Orders")
    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllOrdersResponse> getAll() {
        return orderService.getAll();
    }

    @Operation(summary = "Get all Orders by customerId")
    @GetMapping("/get/all/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllOrdersResponse> getAllByCustomerId(@PathVariable int customerId) {
        return orderService.getAllByCustomerId(customerId);
    }
}
