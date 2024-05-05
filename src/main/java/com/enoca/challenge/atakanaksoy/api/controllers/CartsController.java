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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartsController {
    private final CartService cartService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCartResponse add(@Valid @RequestBody CreateCartRequest createCartRequest) {
        return cartService.add(createCartRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCartResponse update(@Valid @RequestBody UpdateCartRequest updateCartRequest, @PathVariable int id) {
        return cartService.update(updateCartRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        cartService.delete(id);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCartByIdResponse getById(@PathVariable int id) {
        return cartService.getById(id);
    }

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCartsResponse> getAll() {
        return cartService.getAll();
    }

    @PutMapping("/add/product")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCartResponse addProduct(@Valid @RequestBody AddProductToCartRequest addProductToCartRequest) {
        return cartService.addProduct(addProductToCartRequest);
    }

    @PutMapping("/remove/product")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCartResponse removeProduct(@Valid @RequestBody RemoveProductFromCartRequest removeProductFromCartRequest) {
        return cartService.removeProduct(removeProductFromCartRequest);
    }

    @PutMapping("/empty/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void emptyCart(@PathVariable int id) {
        cartService.emptyCart(id);
    }
}
