package com.enoca.challenge.atakanaksoy.api.controllers;

import com.enoca.challenge.atakanaksoy.business.abstracts.CartService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.AddProductToCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.CreateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.RemoveProductFromCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.UpdateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.CreatedCartResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetAllCartsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetCartByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.UpdatedCartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cart Management", description = "Cart Management APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartsController {
    private final CartService cartService;

    @Operation(summary = "Create new Cart")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCartResponse add(@Valid @RequestBody CreateCartRequest createCartRequest) {
        return cartService.add(createCartRequest);
    }

    @Operation(summary = "Update an existing Cart with id")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCartResponse update(@Valid @RequestBody UpdateCartRequest updateCartRequest, @PathVariable int id) {
        return cartService.update(updateCartRequest, id);
    }

    @Operation(summary = "Soft delete an existing Cart with id")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        cartService.delete(id);
    }

    @Operation(summary = "Get an existing Cart with id")
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCartByIdResponse getById(@PathVariable int id) {
        return cartService.getById(id);
    }

    @Operation(summary = "Get an existing Cart with customerId")
    @GetMapping("/get/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCartByIdResponse getByCustomerId(@PathVariable int customerId) {
        return cartService.getByCustomerId(customerId);
    }

    @Operation(summary = "Get all Carts")
    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCartsResponse> getAll() {
        return cartService.getAll();
    }

    @Operation(summary = "Add a Product to Cart")
    @PutMapping("/add/product")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCartResponse addProduct(@Valid @RequestBody AddProductToCartRequest addProductToCartRequest) {
        return cartService.addProduct(addProductToCartRequest);
    }

    @Operation(summary = "Remove a Product from Cart")
    @PutMapping("/remove/product")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCartResponse removeProduct(@Valid @RequestBody RemoveProductFromCartRequest removeProductFromCartRequest) {
        return cartService.removeProduct(removeProductFromCartRequest);
    }

    @Operation(summary = "Empty a Cart with id")
    @PutMapping("/empty/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void emptyCart(@PathVariable int id) {
        cartService.emptyCart(id);
    }
}
